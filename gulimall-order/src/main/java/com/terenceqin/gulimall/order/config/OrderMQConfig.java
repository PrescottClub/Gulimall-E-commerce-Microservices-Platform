package com.terenceqin.gulimall.order.config;

import com.atguigu.common.constant.RabbitInfo;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import static com.atguigu.common.constant.RabbitInfo.Order.baseRoutingKey;

/**
 * Description：容器中的所有bean都会自动创建到RabbitMQ�?[RabbitMQ没有这个队列、交换机、绑定]
 * date�?020/7/3 17:03
 * 创建交换机、队列、bind
 */
@Configuration
public class OrderMQConfig {

	/**
	 * String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
	 * @return
	 */
	@Bean
	public Exchange orderEventExchange(){
		return new TopicExchange( RabbitInfo.Order.exchange,
				true, false);
	}


	/**
	 * String name, boolean durable, boolean exclusive,
	 * boolean autoDelete,
	 * @Nullable Map<String, Object> arguments
	 */
	@Bean
	public Queue orderDelayQueue(){
		Map<String ,Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange", RabbitInfo.Order.exchange);
		// 死信队列的re路由key
		arguments.put("x-dead-letter-routing-key",  RabbitInfo.Order.releaseRoutingKey);
		arguments.put("x-message-ttl",  RabbitInfo.Order.ttl);
		Queue queue = new Queue( RabbitInfo.Order.delayQueue, true, false, false, arguments);
		return queue;
	}

	@Bean
	public Queue orderReleaseOrderQueue(){
		Queue queue = new Queue( RabbitInfo.Order.releaseQueue,
				true,
				false,
				false);
		return queue;
	}


	/**
	 * String destination, DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments
	 */
	@Bean
	public Binding orderCreateOrderBinding(){

		return new Binding( RabbitInfo.Order.delayQueue, Binding.DestinationType.QUEUE,
				RabbitInfo.Order.exchange,  RabbitInfo.Order.delayRoutingKey, null);
	}

	@Bean
	public Binding orderReleaseOrderBinding(){

		return new Binding( RabbitInfo.Order.releaseQueue, Binding.DestinationType.QUEUE,
				RabbitInfo.Order.exchange,  RabbitInfo.Order.releaseRoutingKey, null);
	}

	/**
	 * 订单释放直接和库存释放进行绑�?	 */
	@Bean
	public Binding orderReleaseOtherBinding(){

		return new Binding(RabbitInfo.Order.releaseQueue, Binding.DestinationType.QUEUE,
				RabbitInfo.Order.exchange,
				baseRoutingKey, null);
	}

	@Bean
	public Queue orderSecKillQueue(){
		return new Queue(RabbitInfo.SecKill.delayQueue,
				true, false, false);
	}
	@Bean
	public Binding orderSecKillQueueBinding(){
		return new Binding(RabbitInfo.SecKill.delayQueue, Binding.DestinationType.QUEUE,
				RabbitInfo.Order.exchange, RabbitInfo.SecKill.delayRoutingKey, null);
	}
}
