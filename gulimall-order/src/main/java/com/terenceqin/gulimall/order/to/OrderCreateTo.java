package com.terenceqin.gulimall.order.to;

import com.terenceqin.gulimall.order.entity.OrderEntity;
import com.terenceqin.gulimall.order.entity.OrderItemEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>Title: OrderCreateTo</p>
 * Description�? */
@Data
public class OrderCreateTo {

	private OrderEntity order;

	private List<OrderItemEntity> orderItems;

	/**
	 * 订单计算的应付价�?	 */
	private BigDecimal payPrice;

	/**
	 * 运费
	 */
	private BigDecimal fare;
}
