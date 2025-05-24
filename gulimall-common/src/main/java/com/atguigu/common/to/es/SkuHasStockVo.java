package com.atguigu.common.to.es;

import lombok.Data;

/**
 * <p>Title: SkuHasStockVo</p>
 * Description：存储这个sku是否有库�? * date�?020/6/8 20:10
 */
@Data
public class SkuHasStockVo {

	private Long skuId;

	/**是否有库�?/
	private Boolean hasStock;
}
