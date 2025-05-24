package com.atguigu.common.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * <p>Title: SkuReductionTO</p>
 * Description�? * date�?020/6/5 17:33
 */
@Data
public class SkuReductionTO {

	private Long skuId;

	/***
	 * fullCount、discount、countStatus  打折信息
	 * 买几件、打几折、是否参数其他优�?	 */
	private int fullCount;

	private BigDecimal discount;

	private int countStatus;

	private BigDecimal fullPrice;

	private BigDecimal reducePrice;

	private int priceStatus;

	private List<MemberPrice> memberPrice;
}
