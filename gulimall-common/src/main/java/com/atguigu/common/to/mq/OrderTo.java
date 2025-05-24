package com.atguigu.common.to.mq;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: OrderTo</p>
 * Description�? * date�?020/7/4 15:16
 */
@Data
public class OrderTo {

	private Long id;
	/**
	 * member_id
	 */
	private Long memberId;
	/**
	 * 订单�?	 */
	private String orderSn;
	/**
	 * 使用的优惠券
	 */
	private Long couponId;
	/**
	 * create_time
	 */
	private Date createTime;
	/**
	 * 用户�?	 */
	private String memberUsername;
	/**
	 * 订单总额
	 */
	private BigDecimal totalAmount;
	/**
	 * 应付总额
	 */
	private BigDecimal payAmount;
	/**
	 * 运费金额
	 */
	private BigDecimal freightAmount;
	/**
	 * 促销优化金额（促销价、满减、阶梯价�?	 */
	private BigDecimal promotionAmount;
	/**
	 * 积分抵扣金额
	 */
	private BigDecimal integrationAmount;
	/**
	 * 优惠券抵扣金�?	 */
	private BigDecimal couponAmount;
	/**
	 * 后台调整订单使用的折扣金�?	 */
	private BigDecimal discountAmount;
	/**
	 * 支付方式�?->支付宝；2->微信�?->银联�?4->货到付款；�?	 */
	private Integer payType;
	/**
	 * 订单来源[0->PC订单�?->app订单]
	 */
	private Integer sourceType;
	/**
	 * 订单状态�?->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单�?	 */
	private Integer status;
	/**
	 * 物流公司(配送方�?
	 */
	private String deliveryCompany;
	/**
	 * 物流单号
	 */
	private String deliverySn;
	/**
	 * 自动确认时间（天�?	 */
	private Integer autoConfirmDay;
	/**
	 * 可以获得的积�?	 */
	private Integer integration;
	/**
	 * 可以获得的成长�?	 */
	private Integer growth;
	/**
	 * 发票类型[0->不开发票�?->电子发票�?->纸质发票]
	 */
	private Integer billType;
	/**
	 * 发票抬头
	 */
	private String billHeader;
	/**
	 * 发票内容
	 */
	private String billContent;
	/**
	 * 收票人电�?	 */
	private String billReceiverPhone;
	/**
	 * 收票人邮�?	 */
	private String billReceiverEmail;
	/**
	 * 收货人姓�?	 */
	private String receiverName;
	/**
	 * 收货人电�?	 */
	private String receiverPhone;
	/**
	 * 收货人邮�?	 */
	private String receiverPostCode;
	/**
	 * 省份/直辖�?	 */
	private String receiverProvince;
	/**
	 * 城市
	 */
	private String receiverCity;
	/**
	 * �?	 */
	private String receiverRegion;
	/**
	 * 详细地址
	 */
	private String receiverDetailAddress;
	/**
	 * 订单备注
	 */
	private String note;
	/**
	 * 确认收货状态[0->未确认；1->已确认]
	 */
	private Integer confirmStatus;
	/**
	 * 删除状态�?->未删除；1->已删除�?	 */
	private Integer deleteStatus;
	/**
	 * 下单时使用的积分
	 */
	private Integer useIntegration;
	/**
	 * 支付时间
	 */
	private Date paymentTime;
	/**
	 * 发货时间
	 */
	private Date deliveryTime;
	/**
	 * 确认收货时间
	 */
	private Date receiveTime;
	/**
	 * 评价时间
	 */
	private Date commentTime;
	/**
	 * 修改时间
	 */
	private Date modifyTime;
}
