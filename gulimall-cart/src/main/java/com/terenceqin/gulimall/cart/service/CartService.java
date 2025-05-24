package com.terenceqin.gulimall.cart.service;

import com.terenceqin.gulimall.cart.vo.Cart;
import com.terenceqin.gulimall.cart.vo.CartItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * <p>Title: CartService</p>
 * Description�? * date�?020/6/27 22:17
 */
public interface CartService {

	/**
	 * 将商品添加到购物�?	 */
	CartItem addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

	/**
	 * 获取购物车中某个购物�?	 */
	CartItem getCartItem(Long skuId);

	/**
	 * 获取整个购物�?	 */
	Cart getCart() throws ExecutionException, InterruptedException;

	/**
	 * 清空购物�?	 */
	void clearCart(String cartKey);

	/**
	 * 勾选购物项
	 */
	void checkItem(Long skuId, Integer check);

	/**
	 * 改变购物车中物品的数�?	 */
	void changeItemCount(Long skuId, Integer num);

	/**
	 * 删除购物�?	 */
	void deleteItem(Long skuId);

	/**
	 * 结账
	 */
	BigDecimal toTrade() throws ExecutionException, InterruptedException;

	List<CartItem> getUserCartItems();
}
