package com.terenceqin.gulimall.product.feign;

import com.atguigu.common.to.es.SkuEsModel;
import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>Title: SearchFeignService</p>
 * Description：远程上架商�? * date�?020/6/8 21:42
 */
@FeignClient("gulimall-search")
public interface SearchFeignService {

	@PostMapping("/search/save/product")
	R productStatusUp(@RequestBody List<SkuEsModel> skuEsModels);
}
