package com.terenceqin.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 属性分�? * 
 * @author hh
 * @email 55333@qq.com
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/*** 分组id*/
	@TableId
	private Long attrGroupId;
	/*** 组名*/
	private String attrGroupName;
	/*** 排序*/
	private Integer sort;
	/*** 描述*/
	private String descript;
	/*** 组图�?/
	private String icon;
	/*** 所属分类id*/
	private Long catelogId;

	/**
	 * 三级分类修改的时候回显路�?	 */
	@TableField(exist = false)
	private Long[] catelogPath;
}
