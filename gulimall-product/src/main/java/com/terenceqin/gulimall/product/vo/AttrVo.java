package com.terenceqin.gulimall.product.vo;

import lombok.Data;

/**
 * <p>Title: AttrVo</p>
 * Description�? * date�?020/6/2 19:23
 */
@Data
public class AttrVo {

	/**
	 * 属性id
	 */
	private Long attrId;
	/**
	 * 属性名
	 */
	private String attrName;
	/**
	 * 是否需要检索[0-不需要，1-需要]
	 */
	private Integer searchType;
	/**
	 * 属性图�?	 */
	private String icon;
	/**
	 * 可选值列表[用逗号分隔]
	 */
	private String valueSelect;
	/**
	 * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
	 */
	private Integer attrType;
	/**
	 * 启用状态[0 - 禁用�? - 启用]
	 */
	private Long enable;
	/**
	 * 所属分�?	 */
	private Long catelogId;
	/**
	 * 快速展示【是否展示在介绍上；0-�?1-是】，在sku中仍然可以调�?	 */
	private Integer showDesc;

	private Long attrGroupId;
}
