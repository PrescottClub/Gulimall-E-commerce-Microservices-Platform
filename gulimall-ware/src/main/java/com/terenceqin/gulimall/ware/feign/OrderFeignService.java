package com.terenceqin.gulimall.ware.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>Title: OrderFeignService</p>
 * Description�? * date�?020/7/3 22:15
 */
@FeignClient("mall-order")
public interface OrderFeignService {

	/**
	 * 查询订单状�?	 */
	@GetMapping("/order/order/status/{orderSn}")
	R getOrderStatus(@PathVariable("orderSn") String orderSn);
}
