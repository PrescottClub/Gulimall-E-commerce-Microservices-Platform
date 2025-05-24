package com.terenceqin.gulimall.cart.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>Title: SkuInfoVo</p>
 * Description：用于封装远程返回的Sku对象
 */
@Data
public class SkuInfoVo {

	private Long skuId;
	private Long spuId;
	private String skuName;
	/**
	 * sku介绍描述
	 */
	private String skuDesc;
	private Long catalogId;
	private Long brandId;
	private String skuDefaultImg;
	private String skuTitle;
	private String skuSubtitle;
	private BigDecimal price;
	/**
	 * 销�?	 */
	private Long saleCount;
}
