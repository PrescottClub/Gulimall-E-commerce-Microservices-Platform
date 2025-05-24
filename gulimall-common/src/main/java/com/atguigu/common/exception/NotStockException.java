package com.atguigu.common.exception;

/**
 * <p>Title: NotStockException</p>
 * Description�? * date�?020/7/2 11:43
 */
public class NotStockException extends RuntimeException{

	private Long skuId;

	public NotStockException(String msg) {
		super(msg + "号商品没有足够的库存�?);
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}
}
