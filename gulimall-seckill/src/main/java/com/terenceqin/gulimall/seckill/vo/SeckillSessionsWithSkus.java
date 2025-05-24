package com.terenceqin.gulimall.seckill.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: SeckillSessionsWithSkus</p>
 * Description�? * date�?020/7/6 18:48
 */
@Data
public class SeckillSessionsWithSkus {

	private Long id;
	/**
	 * 场次名称
	 */
	private String name;
	/**
	 * 每日开始时�?	 */
	private Date startTime;
	/**
	 * 每日结束时间
	 */
	private Date endTime;
	/**
	 * 启用状�?	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;

	private List<SeckillSkuRelationEntity> relationSkus;
}
