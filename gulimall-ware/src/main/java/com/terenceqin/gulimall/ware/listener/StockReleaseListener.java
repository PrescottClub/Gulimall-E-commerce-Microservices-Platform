package com.terenceqin.gulimall.ware.listener;

import com.atguigu.common.constant.RabbitInfo;
import com.atguigu.common.to.mq.OrderTo;
import com.atguigu.common.to.mq.StockLockedTo;
import com.terenceqin.gulimall.ware.service.WareSkuService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * <p>Title: StockReleaseListener</p>
 * Description：监听锁库存消息，有错误的话根据消息回滚库存
 * date�?020/7/3 23:56
 */
@Service
@RabbitListener(queues = RabbitInfo.Stock.releaseQueue)
public class StockReleaseListener {

	@Autowired
	private WareSkuService wareSkuService;

	/**
	 * 下单成功 库存解锁 接下来业务调用失�?	 *
	 *  只要解锁库存消息失败 一定要告诉服务解锁失败
	 */
	@RabbitHandler
	public void handleStockLockedRelease(StockLockedTo to,
										 Message message,
										 Channel channel) throws IOException {
		try {
			wareSkuService.unlockStock(to); // 要根据id
			// 执行成功�?回复 [仅回复自己的消息]
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
		}
	}

	/**
	 * 订单关闭�?发送的消息这里接收
	 */
	@RabbitHandler
	public void handleOrderCloseRelease(OrderTo to, Message message,
										Channel channel) throws IOException {
		try {
			wareSkuService.unlockStock(to);
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
		} catch (Exception e) {
			channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
		}
	}
}
