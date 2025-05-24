package com.terenceqin.gulimall.product.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>Title: WareFeignService</p>
 * Description�? * date�?020/6/8 20:26
 */
@FeignClient("gulimall-ware")
public interface WareFeignService {

	/**
	 * 修改真个系统�?R 带上泛型
	 */
	@PostMapping("/ware/waresku/hasStock")
//	List<SkuHasStockVo> getSkuHasStock(@RequestBody List<Long> SkuIds);
	R getSkuHasStock(@RequestBody List<Long> SkuIds);
}
