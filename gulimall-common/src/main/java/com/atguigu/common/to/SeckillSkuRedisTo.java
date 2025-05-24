package com.atguigu.common.to;

//import com.terenceqin.gulimall.seckill.vo.SkuInfoVo;
import com.atguigu.common.vo.SkuInfoVo;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>Title: SeckillSkuRedisTo</p>
 */
@Data
public class SeckillSkuRedisTo {

	private Long promotionId;
	/**
	 * 活动场次id
	 */
	private Long promotionSessionId;
	/**
	 * 商品id
	 */
	private Long skuId;
	/**
	 * 商品的秒杀随机�?	 */
	private String randomCode;
	/**
	 * 秒杀价格
	 */
	private BigDecimal seckillPrice;
	/**
	 * 秒杀总量
	 */
	private BigDecimal seckillCount;
	/**
	 * 每人限购数量
	 */
	private BigDecimal seckillLimit;
	/**
	 * 排序
	 */
	private Integer seckillSort;

	/**
	 *  sku的详细信�?	 */
	private SkuInfoVo skuInfoVo;

	/**
	 *  商品秒杀的开始时�?	 */
	private Long startTime;

	/**
	 *  商品秒杀的结束时�?	 */
	private Long endTime;
}
