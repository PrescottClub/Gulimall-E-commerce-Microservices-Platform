package com.terenceqin.gulimall.order.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Title: ProductFeignService</p>
 * Description�? * date�?020/7/2 0:43
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {

	@GetMapping("/product/spuinfo/skuId/{id}")
	R getSpuInfoBySkuId(@PathVariable("id") Long skuId);
}
