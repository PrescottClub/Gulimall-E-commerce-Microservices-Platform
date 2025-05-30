package com.terenceqin.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * ååå±æ? * 
 * @author hh
 * @email 55333@qq.com
 * @date 2020-06-23 20:09:14
 */
@Data
@TableName("pms_attr")
public class AttrEntity implements Serializable {
	private static final long serialVersionUID = 1L;


	private Integer valueType;
	/**
	 * å±æ§id
	 */
	@TableId
	private Long attrId;
	/**
	 * å±æ§å
	 */
	private String attrName;
	/**
	 * æ¯å¦éè¦æ£ç´¢[0-ä¸éè¦ï¼1-éè¦]
	 */
	private Integer searchType;
	/**
	 * å±æ§å¾æ ?	 */
	private String icon;
	/**
	 * å¯éå¼åè¡¨[ç¨éå·åé]
	 */
	private String valueSelect;
	/**
	 * å±æ§ç±»å[0-éå®å±æ§ï¼1-åºæ¬å±æ§ï¼2-æ¢æ¯éå®å±æ§åæ¯åºæ¬å±æ§]
	 */
	private Integer attrType;
	/**
	 * å¯ç¨ç¶æ[0 - ç¦ç¨ï¼? - å¯ç¨]
	 */
	private Long enable;
	/**
	 * æå±åç±?	 */
	private Long catelogId;
	/**
	 * å¿«éå±ç¤ºãæ¯å¦å±ç¤ºå¨ä»ç»ä¸ï¼0-å?1-æ¯ãï¼å¨skuä¸­ä»ç¶å¯ä»¥è°æ?	 */
	private Integer showDesc;

}
