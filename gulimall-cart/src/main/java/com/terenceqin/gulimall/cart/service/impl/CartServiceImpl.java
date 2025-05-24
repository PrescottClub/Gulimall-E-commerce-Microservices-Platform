package com.terenceqin.gulimall.cart.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.utils.R;
import com.terenceqin.gulimall.cart.service.CartService;
import com.terenceqin.gulimall.cart.feign.ProductFeignService;
import com.terenceqin.gulimall.cart.interceptor.CartInterceptor;
import com.terenceqin.gulimall.cart.vo.Cart;
import com.terenceqin.gulimall.cart.vo.CartItem;
import com.terenceqin.gulimall.cart.vo.SkuInfoVo;
import com.terenceqin.gulimall.cart.vo.UserInfoTo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * <p>Title: CartServiceImpl</p>
 * Description�? */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProductFeignService productFeignService;

    @Autowired
    private ThreadPoolExecutor executor;

    private final String CART_PREFIX = "ATGUIGU:cart:";

    @Override // CartServiceImpl
    public CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        // 获取当前用户的map
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        // 查看该用户购物车里是否有指定的skuId
        String res = (String) cartOps.get(skuId.toString());

        // 查看用户购物车里是否已经有了该sku�?        if (StringUtils.isEmpty(res)) {
            CartItem cartItem = new CartItem();
            // 异步编排
            CompletableFuture<Void> getSkuInfo = CompletableFuture.runAsync(() -> {
                // 1. 远程查询当前要添加的商品的信�?                R skuInfo = productFeignService.SkuInfo(skuId);
                SkuInfoVo sku = skuInfo.getData("skuInfo", new TypeReference<SkuInfoVo>() {
                });
                // 2. 填充购物�?                cartItem.setCount(num);
                cartItem.setCheck(true);
                cartItem.setImage(sku.getSkuDefaultImg());
                cartItem.setPrice(sku.getPrice());
                cartItem.setTitle(sku.getSkuTitle());
                cartItem.setSkuId(skuId);
            }, executor);

            // 3. 远程查询sku销售属性，销售属性是个list
            CompletableFuture<Void> getSkuSaleAttrValues = CompletableFuture.runAsync(() -> {
                List<String> values = productFeignService.getSkuSaleAttrValues(skuId);
                cartItem.setSkuAttr(values);
            }, executor);
            // 等待执行完成
            CompletableFuture.allOf(getSkuInfo, getSkuSaleAttrValues).get();

            // sku放到用户购物车redis�?            cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
            return cartItem;
        } else {//购物车里已经有该sku了，数量+1即可
            CartItem cartItem = JSON.parseObject(res, CartItem.class);
            // 不太可能并发，无需加锁
            cartItem.setCount(cartItem.getCount() + num);
            cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
            return cartItem;
        }
    }

    @Override
    public CartItem getCartItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        String o = (String) cartOps.get(skuId.toString());
        return JSON.parseObject(o, CartItem.class);
    }

    /**
     * 获取并合并购物车
     */
    @Override
    public Cart getCart() throws ExecutionException, InterruptedException {
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        Cart cart = new Cart();
        // 临时购物车的key // 用户key在哪里设置的以后研究一�?        String tempCartKey = CART_PREFIX + userInfoTo.getUserKey();
        // 简单处理一下，以后修改
        if ("ATGUIGU:cart:".equals(tempCartKey)) tempCartKey += "X";

        // 是否登录
        if (userInfoTo.getUserId() != null) {
            // 已登�?对用户的购物车进行操�?            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            // 1 如果临时购物车的数据没有进行合并
            List<CartItem> tempItem = getCartItems(tempCartKey);
            if (tempItem != null) {
                // 2 临时购物车有数据 则进行合�?                log.info("\n[" + userInfoTo.getUsername() + "] 的购物车已合�?);
                for (CartItem cartItem : tempItem) {
                    addToCart(cartItem.getSkuId(), cartItem.getCount());
                }
                // 3 清空临时购物�?防止重复添加
                clearCart(tempCartKey);
                // 设置为非临时用户
                userInfoTo.setTempUser(false);
            }
            // 4 获取登录后的购物车数�?[包含合并过来的临时购物车数据]
            List<CartItem> cartItems = getCartItems(cartKey);
            cart.setItems(cartItems);
        } else {
            // 没登�?获取临时购物车的所有购物项
            cart.setItems(getCartItems(tempCartKey));
        }
        return cart;
    }

    @Override
    public void clearCart(String cartKey) {
        stringRedisTemplate.delete(cartKey);
    }

    @Override
    public void checkItem(Long skuId, Integer check) {
        // 获取要选中的购物项
        CartItem cartItem = getCartItem(skuId);
        // 切换购物车选择状�?        cartItem.setCheck(check == 1 ? true : false);
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
    }

    @Override
    public void changeItemCount(Long skuId, Integer num) {
        CartItem cartItem = getCartItem(skuId);
        if (cartItem == null) {
            return;
        }
        cartItem.setCount(num);
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.put(skuId.toString(), JSON.toJSONString(cartItem));
    }

    @Override
    public void deleteItem(Long skuId) {
        BoundHashOperations<String, Object, Object> cartOps = getCartOps();
        cartOps.delete(skuId.toString());
    }

    @Override
    public BigDecimal toTrade() throws ExecutionException, InterruptedException {
        BigDecimal amount = getCart().getTotalAmount();
        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        stringRedisTemplate.delete(CART_PREFIX + (userInfoTo.getUserId() != null ? userInfoTo.getUserId().toString() : userInfoTo.getUserKey()));
        return amount;
    }

    @Override
    public List<CartItem> getUserCartItems() {

        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        if (userInfoTo.getUserId() == null) {
            return null;
        } else {
            String cartKey = CART_PREFIX + userInfoTo.getUserId();
            List<CartItem> cartItems = getCartItems(cartKey);
            // 获取所有被选中的购物项
            List<CartItem> collect = cartItems.stream().filter(item -> item.getCheck()).map(item -> {
                try {
                    // 因为redis中的价格可能已经不匹配了，所以重新获取一�?                    R r = productFeignService.getPrice(item.getSkuId());
                    String price = (String) r.get("data");
                    item.setPrice(new BigDecimal(price));
                } catch (Exception e) {
                    log.warn("远程查询商品价格出错 [商品服务未启动]");
                }
                return item;
            }).collect(Collectors.toList());
            return collect;
        }
    }

    /**
     * 获取购物车所有项
     */
    private List<CartItem> getCartItems(String cartKey) {
        BoundHashOperations<String, Object, Object> hashOps = stringRedisTemplate.boundHashOps(cartKey);
        // <skuId,CartItem>
        List<Object> values = hashOps.values();
        // JSON.toJSONString(obj)的结果是 "{\"check\":  多了个String
        // (String)obj 的结果是 {"check"
        // 使用JSON.toJSONString(obj)会报�?        if (values != null && values.size() > 0) {
            return values.stream().map(
                    obj -> JSON.parseObject((String) obj, CartItem.class)).collect(Collectors.toList());
        }
        return null;
    }

    /**
     * 用户购物车redis-map，还没合并登录和临时购物�?     */
    private BoundHashOperations<String, Object, Object> getCartOps() {
        // 1. 这里我们需要知道操作的是离线购物车还是在线购物�?        UserInfoTo userInfoTo = CartInterceptor.threadLocal.get();
        String cartKey = CART_PREFIX;
        // 根据userId区别是登录用户还是临时用�?        if (userInfoTo.getUserId() != null) {
            log.debug("\n用户 [" + userInfoTo.getUsername() + "] 正在操作购物�?);
            // 已登录的用户购物车的标识
            cartKey += userInfoTo.getUserId();
        } else {
            log.debug("\n临时用户 [" + userInfoTo.getUserKey() + "] 正在操作购物�?);
            // 未登录的用户购物车的标识
            cartKey += userInfoTo.getUserKey();
        }
        // 绑定这个 key 以后所有对redis 的操作都是针对这个key
        return stringRedisTemplate.boundHashOps(cartKey);
    }
}
