package com.terenceqin.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 首页轮播广告
 * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-24 12:50:21
 */
@Data
@TableName("sms_home_adv")
public class HomeAdvEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 名字
	 */
	private String name;
	/**
	 * 图片地址
	 */
	private String pic;
	/**
	 * 开始时�?	 */
	private Date startTime;
	/**
	 * 结束时间
	 */
	private Date endTime;
	/**
	 * 状�?	 */
	private Integer status;
	/**
	 * 点击�?	 */
	private Integer clickCount;
	/**
	 * 广告详情连接地址
	 */
	private String url;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 发布�?	 */
	private Long publisherId;
	/**
	 * 审核�?	 */
	private Long authId;

}
