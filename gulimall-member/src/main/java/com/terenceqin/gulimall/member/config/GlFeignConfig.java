package com.terenceqin.gulimall.member.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: GlFeignConfig</p>
 * Description�? * date�?020/7/4 23:50
 */
@Configuration
public class GlFeignConfig {

	@Bean("requestInterceptor")
	public RequestInterceptor requestInterceptor(){
		// Feign在远程调用之前都会先经过这个方法
		return (template) -> {
			// RequestContextHolder拿到刚进来这个请�?			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			if(request != null){
				// 同步请求头数�?				String cookie = request.getHeader("Cookie");
				// 给新请求同步Cookie
				template.header("Cookie", cookie);
			}
		};
	}
}
