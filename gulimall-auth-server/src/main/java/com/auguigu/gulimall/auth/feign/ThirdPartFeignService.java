package com.auguigu.gulimall.auth.feign;

import com.atguigu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>Title: ThirdPartFeignService</p>
 * Descriptionï¼? * dateï¼?020/6/25 15:01
 */
@FeignClient("gulimall-third-party")
public interface ThirdPartFeignService {

	@GetMapping("/sms/sendcode")
	R sendCode(@RequestParam("phone") String phone, @RequestParam("code") String code);
}
