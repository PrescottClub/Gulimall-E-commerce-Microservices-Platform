package com.terenceqin.gulimall.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员统计信息
 * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-24 13:05:05
 */
@Data
@TableName("ums_member_statistics_info")
public class MemberStatisticsInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * 会员id
	 */
	private Long memberId;
	/**
	 * 累计消费金额
	 */
	private BigDecimal consumeAmount;
	/**
	 * 累计优惠金额
	 */
	private BigDecimal couponAmount;
	/**
	 * 订单数量
	 */
	private Integer orderCount;
	/**
	 * 优惠券数�?	 */
	private Integer couponCount;
	/**
	 * 评价�?	 */
	private Integer commentCount;
	/**
	 * 退货数�?	 */
	private Integer returnOrderCount;
	/**
	 * 登录次数
	 */
	private Integer loginCount;
	/**
	 * 关注数量
	 */
	private Integer attendCount;
	/**
	 * 粉丝数量
	 */
	private Integer fansCount;
	/**
	 * 收藏的商品数�?	 */
	private Integer collectProductCount;
	/**
	 * 收藏的专题活动数�?	 */
	private Integer collectSubjectCount;
	/**
	 * 收藏的评论数�?	 */
	private Integer collectCommentCount;
	/**
	 * 邀请的朋友数量
	 */
	private Integer inviteFriendCount;

}
