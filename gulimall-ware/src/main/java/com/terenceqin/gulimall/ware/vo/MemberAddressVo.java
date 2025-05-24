package com.terenceqin.gulimall.ware.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * <p>Title: MemberAddressVo</p>
 * Description�? * date�?020/6/30 16:22
 */
@Data
public class MemberAddressVo {

	@TableId
	private Long id;
	/**
	 * member_id
	 */
	private Long memberId;
	/**
	 * 收货人姓�?	 */
	private String name;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 邮政编码
	 */
	private String postCode;
	/**
	 * 省份/直辖�?	 */
	private String province;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * �?	 */
	private String region;
	/**
	 * 详细地址(街道)
	 */
	private String detailAddress;
	/**
	 * 省市区代�?	 */
	private String areacode;
	/**
	 * 是否默认
	 */
	private Integer defaultStatus;

}
