package com.terenceqin.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品阶梯价格
 * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-24 12:50:21
 */
@Data
@TableName("sms_sku_ladder")
public class SkuLadderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * spu_id
	 */
	private Long skuId;
	/**
	 * 满几�?	 */
	private Integer fullCount;
	/**
	 * 打几�?	 */
	private BigDecimal discount;
	/**
	 * 折后�?	 */
	private BigDecimal price;
	/**
	 * 是否叠加其他优惠[0-不可叠加�?-可叠加]
	 */
	private Integer addOther;

}
