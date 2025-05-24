package com.terenceqin.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单退货申�? * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-23 20:09:14
 */
@Data
@TableName("oms_order_return_apply")
public class OmsOrderReturnApplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * order_id
	 */
	private Long orderId;
	/**
	 * 退货商品id
	 */
	private Long skuId;
	/**
	 * 订单编号
	 */
	private String orderSn;
	/**
	 * 申请时间
	 */
	private Date createTime;
	/**
	 * 会员用户�?	 */
	private String memberUsername;
	/**
	 * 退款金�?	 */
	private BigDecimal returnAmount;
	/**
	 * 退货人姓名
	 */
	private String returnName;
	/**
	 * 退货人电话
	 */
	private String returnPhone;
	/**
	 * 申请状态[0->待处理；1->退货中�?->已完成；3->已拒绝]
	 */
	private Integer status;
	/**
	 * 处理时间
	 */
	private Date handleTime;
	/**
	 * 商品图片
	 */
	private String skuImg;
	/**
	 * 商品名称
	 */
	private String skuName;
	/**
	 * 商品品牌
	 */
	private String skuBrand;
	/**
	 * 商品销售属�?JSON)
	 */
	private String skuAttrsVals;
	/**
	 * 退货数�?	 */
	private Integer skuCount;
	/**
	 * 商品单价
	 */
	private BigDecimal skuPrice;
	/**
	 * 商品实际支付单价
	 */
	private BigDecimal skuRealPrice;
	/**
	 * 原因
	 */
	private String reason;
	/**
	 * 描述
	 */
	private String description�?
	/**
	 * 凭证图片，以逗号隔开
	 */
	private String descPics;
	/**
	 * 处理备注
	 */
	private String handleNote;
	/**
	 * 处理人员
	 */
	private String handleMan;
	/**
	 * 收货�?	 */
	private String receiveMan;
	/**
	 * 收货时间
	 */
	private Date receiveTime;
	/**
	 * 收货备注
	 */
	private String receiveNote;
	/**
	 * 收货电话
	 */
	private String receivePhone;
	/**
	 * 公司收货地址
	 */
	private String companyAddress;

}
