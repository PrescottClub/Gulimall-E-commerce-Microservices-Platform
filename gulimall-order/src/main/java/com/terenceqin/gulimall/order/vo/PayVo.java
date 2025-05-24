package com.terenceqin.gulimall.order.vo;

import lombok.Data;

@Data
public class PayVo {
	/**
	 *  商户订单�?必填
	 */
    private String out_trade_no;

	/**
	 * 订单名称 必填
	 */
	private String subject;

	/**
	 * 付款金额 必填
	 */
    private String total_amount;

	/**
	 * 商品描述 可空
	 */
	private String body;
}
