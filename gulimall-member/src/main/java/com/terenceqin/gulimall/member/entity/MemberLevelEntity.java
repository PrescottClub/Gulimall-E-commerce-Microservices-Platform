package com.terenceqin.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员等级
 * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-24 13:05:05
 */
@Data
@TableName("ums_member_level")
public class MemberLevelEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 等级名称
	 */
	private String name;
	/**
	 * 等级需要的成长�?	 */
	private Integer growthPoint;
	/**
	 * 是否为默认等级[0->不是�?->是]
	 */
	private Integer defaultStatus;
	/**
	 * 免运费标�?	 */
	private BigDecimal freeFreightPoint;
	/**
	 * 每次评价获取的成长�?	 */
	private Integer commentGrowthPoint;
	/**
	 * 是否有免邮特�?	 */
	private Integer priviledgeFreeFreight;
	/**
	 * 是否有会员价格特�?	 */
	private Integer priviledgeMemberPrice;
	/**
	 * 是否有生日特�?	 */
	private Integer priviledgeBirthday;
	/**
	 * 备注
	 */
	private String note;

}
