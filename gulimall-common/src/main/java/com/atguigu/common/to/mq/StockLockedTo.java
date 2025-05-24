package com.atguigu.common.to.mq;

import lombok.Data;

/**
 * <p>Title: StockLockedTo</p>
 * Description�? * date�?020/7/3 20:37
 */
@Data
public class StockLockedTo {

	/**
	 * 库存工作单id
	 */
	private Long id;

	/**
	 * 工作详情id
	 */
	private StockDetailTo detailTo;
}
