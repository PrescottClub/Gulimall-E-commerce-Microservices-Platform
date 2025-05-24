package com.terenceqin.gulimall.order.service;

import com.atguigu.common.to.mq.SecKillOrderTo;
import com.atguigu.common.utils.PageUtils;
import com.terenceqin.gulimall.order.entity.OrderEntity;
import com.terenceqin.gulimall.order.vo.*;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author firenay
 * @email 1046762075@qq.com
 * @date 2020-05-30 00:54:56
 */
public interface OrderService extends IService<OrderEntity> {

    PageUtils queryPage(Map<String, Object> params);

	/**
	 * 给订单确认页返回需要的数据
	 */
	OrderConfirmVo confirmOrder() throws ExecutionException, InterruptedException;

	/**
	 * 下单操作
	 */
	SubmitOrderResponseVo submitOrder(OrderSubmitVo submitVo);

	OrderEntity getOrderByOrderSn(String orderSn);

	void closeOrder(OrderEntity entity);

	/**
	 * 获取当前订单的支付信�?	 */
	PayVo getOrderPay(String orderSn);

	PageUtils queryPageWithItem(@Param("params") Map<String, Object> params);

	/**
	 * 处理支付宝的返回数据
	 */
	String handlePayResult(PayAsyncVo vo);

	void createSecKillOrder(SecKillOrderTo secKillOrderTo);
}

