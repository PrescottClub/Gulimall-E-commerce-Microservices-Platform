package com.terenceqin.gulimall.order.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * <p>Title: MyRabbitConfig</p>
 * Description：配置序列化方式
 * date�?020/6/29 14:29
 */
@Slf4j
@Configuration
public class OrderRabbitConfig {

//	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Primary
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		this.rabbitTemplate = rabbitTemplate;
		rabbitTemplate.setMessageConverter(messageConverter());
		// 设置回调
		initRabbitTemplate();
		return rabbitTemplate;
	}

	@Bean
	public MessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	/**
	 * 1.设置确认回调�?ConfirmCallback
	 * 先在配置文件中开�?publisher-confirms: true
	 * @PostConstruct: MyRabbitConfig对象创建完成以后 执行这个方法
	 *
	 *  2.消息抵达队列的确认回�?	 * 　	开启发送端消息抵达队列确认
	 *     publisher-returns: true
	 *     	只要抵达队列，以异步优先回调我们这个 returnconfirm
	 *     template:
	 *       mandatory: true
	 *	3.消费端确�?保证每一个消息被正确消费才可以broker删除消息)
	 *		1.默认是自动确认的 只要消息接收�?服务端就会移除这个消�?	 *
	 *		如何签收:
	 *			签收: channel.basicAck(deliveryTag, false);
	 *			拒签: channel.basicNack(deliveryTag, false,true);
	 *	配置文件中一定要加上这个配置
	 *		listener:
	 *       simple:
	 *         acknowledge-mode: manual
	 */
//	@PostConstruct
	public void initRabbitTemplate(){
		/**
		 * 		设置确认回调  消息到达broker
		 *  correlationData: 消息的唯一id
		 *  ack�?消息是否成功收到
		 * 	cause：失败的原因
		 */
		rabbitTemplate.setConfirmCallback(
				(correlationData, ack , cause) ->
						log.info("\nbroker收到消息: " + correlationData + "\tack: " + ack + "\tcause�?" + cause));

		/**
		 * 只要消息没有投递给指定的队列，就触发这个失败回�?		 *
		 * message: 投递失败的消息详细信息
		 * replyCode: 回复的状态码
		 * replyText: 回复的文本内�?		 * exchange: 当时这个发送给那个交换�?		 * routerKey: 当时这个消息用那个路由键
		 */
		rabbitTemplate.setReturnCallback(
				(message, replyCode, replyText, exchange, routerKey)
						-> log.error("Fail Message [" + message + "]" + "\treplyCode: " + replyCode + "\treplyText:" + replyText + "\texchange:" + exchange + "\trouterKey:" + routerKey));
	}
}
