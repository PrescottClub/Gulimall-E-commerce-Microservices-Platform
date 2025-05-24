package com.terenceqin.gulimall.order.vo;

import lombok.Data;

import java.util.List;

/**
 * <p>Title: WareSkuLockVo</p>
 * Description�? * date�?020/7/2 11:13
 */
@Data
public class WareSkuLockVo {

	/**
	 * 订单�?	 */
	private String orderSn;

	/**
	 * 要锁住的所有库存信�?	 */
	private List<OrderItemVo> locks;

}
