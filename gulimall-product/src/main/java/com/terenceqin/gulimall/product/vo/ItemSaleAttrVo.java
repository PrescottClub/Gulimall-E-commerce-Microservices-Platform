package com.terenceqin.gulimall.product.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class ItemSaleAttrVo{
	private Long attrId;

	private String attrName;

	/** AttrValueWithSkuIdVo两个属�?attrValue、skuIds */
	private List<AttrValueWithSkuIdVo> attrValues;
}
