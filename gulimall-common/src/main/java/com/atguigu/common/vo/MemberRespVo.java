package com.atguigu.common.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: MemberRsepVo</p>
 * Description�? * date�?020/6/26 17:17
 */
@ToString
@Data
public class MemberRespVo implements Serializable {
	private Long id;
	/**
	 * 会员等级id
	 */
	private Long levelId;
	/**
	 * 用户�?	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 昵称
	 */
	private String nickname;
	/**
	 * 手机号码
	 */
	private String mobile;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 头像
	 */
	private String header;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 生日
	 */
	private Date birth;
	/**
	 * 所在城�?	 */
	private String city;
	/**
	 * 职业
	 */
	private String job;
	/**
	 * 个性签�?	 */
	private String sign;
	/**
	 * 用户来源
	 */
	private Integer sourceType;
	/**
	 * 积分
	 */
	private Integer integration;
	/**
	 * 成长�?	 */
	private Integer growth;
	/**
	 * 启用状�?	 */
	private Integer status;
	/**
	 * 注册时间
	 */
	private Date createTime;

	private String socialUid;

	private String accessToken;

	private Long expiresIn;
}
