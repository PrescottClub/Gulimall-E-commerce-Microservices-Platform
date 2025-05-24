package com.terenceqin.gulimall.order.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: MyThreadConfig</p>
 * Description：配置线程池
 * date�?020/6/25 10:58
 */
// 开启这个属性配�?@EnableConfigurationProperties(ThreadPoolConfigProperties.class)
@Configuration
public class OrderThreadConfig {

	@Bean
	public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties threadPoolConfigProperties){

		return new ThreadPoolExecutor(threadPoolConfigProperties.getCoreSize(),
				threadPoolConfigProperties.getMaxSize(),
				threadPoolConfigProperties.getKeepAliveTime() ,TimeUnit.SECONDS,
				new LinkedBlockingDeque<>(10000), Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy());
	}
}
