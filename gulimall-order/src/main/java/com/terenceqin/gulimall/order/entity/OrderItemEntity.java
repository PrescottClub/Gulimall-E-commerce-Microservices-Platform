package com.terenceqin.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * è®¢åé¡¹ä¿¡æ? * 
 */
@Data
@TableName("oms_order_item")
public class OrderItemEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;
	/**
	 * order_id
	 */
	private Long orderId;
	/**
	 * order_sn
	 */
	private String orderSn;
	/**
	 * spu_id
	 */
	private Long spuId;
	/**
	 * spu_name
	 */
	private String spuName;
	/**
	 * spu_pic
	 */
	private String spuPic;
	/**
	 * åç
	 */
	private String spuBrand;
	/**
	 * åååç±»id
	 */
	private Long categoryId;
	/**
	 * ååskuç¼å·
	 */
	private Long skuId;
	/**
	 * ååskuåå­
	 */
	private String skuName;
	/**
	 * ååskuå¾ç
	 */
	private String skuPic;
	/**
	 * ååskuä»·æ ¼
	 */
	private BigDecimal skuPrice;
	/**
	 * ååè´­ä¹°çæ°é?	 */
	private Integer skuQuantity;
	/**
	 * ååéå®å±æ§ç»åï¼JSONï¼?	 */
	private String skuAttrsVals;
	/**
	 * ååä¿éåè§£éé¢
	 */
	private BigDecimal promotionAmount;
	/**
	 * ä¼æ å¸ä¼æ åè§£éé¢?	 */
	private BigDecimal couponAmount;
	/**
	 * ç§¯åä¼æ åè§£éé¢
	 */
	private BigDecimal integrationAmount;
	/**
	 * è¯¥ååç»è¿ä¼æ åçåè§£éé¢?	 */
	private BigDecimal realAmount;
	/**
	 * èµ éç§¯å?	 */
	private Integer giftIntegration;
	/**
	 * èµ éæé¿å?	 */
	private Integer giftGrowth;

}
