package com.terenceqin.gulimall.seckill.config;

import com.terenceqin.gulimall.seckill.interceptor.LoginUserInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * <p>Title: SeckillWebConfig</p>
 * Description´╝? */
@Configuration
public class SeckillWebConfig implements WebMvcConfigurer {

	@Autowired
	private LoginUserInterceptor loginUserInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginUserInterceptor).addPathPatterns("/**");
	}
}
