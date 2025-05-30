package com.terenceqin.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单配置信息
 * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-23 20:09:14
 */
@Data
@TableName("oms_order_setting")
public class OmsOrderSettingEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 秒杀订单超时关闭时间(�?
	 */
	private Integer flashOrderOvertime;
	/**
	 * 正常订单超时时间(�?
	 */
	private Integer normalOrderOvertime;
	/**
	 * 发货后自动确认收货时间（天）
	 */
	private Integer confirmOvertime;
	/**
	 * 自动完成交易时间，不能申请退货（天）
	 */
	private Integer finishOvertime;
	/**
	 * 订单完成后自动好评时间（天）
	 */
	private Integer commentOvertime;
	/**
	 * 会员等级�?-不限会员等级，全部通用；其�?对应的其他会员等级�?	 */
	private Integer memberLevel;

}
