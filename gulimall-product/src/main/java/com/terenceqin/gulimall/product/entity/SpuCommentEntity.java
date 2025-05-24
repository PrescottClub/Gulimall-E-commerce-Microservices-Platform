package com.terenceqin.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 商品评价
 * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-23 20:09:14
 */
@Data
@TableName("pms_spu_comment")
public class SpuCommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * sku_id
	 */
	private Long skuId;
	/**
	 * spu_id
	 */
	private Long spuId;
	/**
	 * 商品名字
	 */
	private String spuName;
	/**
	 * 会员昵称
	 */
	private String memberNickName;
	/**
	 * 星级
	 */
	private Integer star;
	/**
	 * 会员ip
	 */
	private String memberIp;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 显示状态[0-不显示，1-显示]
	 */
	private Integer showStatus;
	/**
	 * 购买时属性组�?	 */
	private String spuAttributes;
	/**
	 * 点赞�?	 */
	private Integer likesCount;
	/**
	 * 回复�?	 */
	private Integer replyCount;
	/**
	 * 评论图片/视频[json数据；[{type:文件类型,url:资源路径}]]
	 */
	private String resources;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 用户头像
	 */
	private String memberIcon;
	/**
	 * 评论类型[0 - 对商品的直接评论�? - 对评论的回复]
	 */
	private Integer commentType;

}
