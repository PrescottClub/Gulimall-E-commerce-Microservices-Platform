package com.terenceqin.gulimall.order.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: OrderConfirmVo</p>
 * Description：订单确认页需要的数据
 * date�?020/6/30 16:20
 */
@Data
public class OrderConfirmVo {

	List<MemberAddressVo> address;

	/**
	 * 所有选中的购物项
	 */
	List<OrderItemVo> items;

	/**
	 * 积分信息
	 */
	private Integer integration;

	/**
	 * 防重令牌
	 */
	private String orderToken;

	Map<Long,Boolean> stocks;

	/**
	 * 获取商品总价�?	 */
	public BigDecimal getTotal() {
		BigDecimal sum = new BigDecimal("0");
		if(items!= null){
			for (OrderItemVo item : items) {
				sum = sum.add(item.getPrice().multiply(new BigDecimal(item.getCount().toString())));
			}
		}
		return sum;
	}

	/**
	 * 应付的价�?	 */
	public BigDecimal getPayPrice() {
		return getTotal();
	}

	public Integer getCount(){
		Integer i = 0;
		if(items!= null){
			for (OrderItemVo item : items) {
				i += item.getCount();
			}
		}
		return i;
	}
	/**
	 * 发票信息...
	 */
}
