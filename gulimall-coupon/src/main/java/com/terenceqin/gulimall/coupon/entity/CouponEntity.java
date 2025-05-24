package com.terenceqin.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 优惠券信�? * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-24 12:50:21
 */
@Data
@TableName("sms_coupon")
public class CouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 优惠卷类型[0->全场赠券�?->会员赠券�?->购物赠券�?->注册赠券]
	 */
	private Integer couponType;
	/**
	 * 优惠券图�?	 */
	private String couponImg;
	/**
	 * 优惠卷名�?	 */
	private String couponName;
	/**
	 * 数量
	 */
	private Integer num;
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 每人限领张数
	 */
	private Integer perLimit;
	/**
	 * 使用门槛
	 */
	private BigDecimal minPoint;
	/**
	 * 开始时�?	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 使用类型[0->全场通用�?->指定分类�?->指定商品]
	 */
	private Integer useType;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 发行数量
	 */
	private Integer publishCount;
	/**
	 * 已使用数�?	 */
	private Integer useCount;
	/**
	 * 领取数量
	 */
	private Integer receiveCount;
	/**
	 * 可以领取的开始日�?	 */
	private Date enableStartTime;
	/**
	 * 可以领取的结束日�?	 */
	private Date enableEndTime;
	/**
	 * 优惠�?	 */
	private String code;
	/**
	 * 可以领取的会员等级[0->不限等级，其�?对应等级]
	 */
	private Integer memberLevel;
	/**
	 * 发布状态[0-未发布，1-已发布]
	 */
	private Integer publish;

}
