package com.terenceqin.gulimall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
* 如何使用nacos作为配置中心�?* 引入依赖nacos-config
* 创建bootstrap.properties里面写上当前项目名字和配置中心地址
* 在nacos配置中心中添加一个当前项目的.properties的文件，写上配置
* @RefreshScope  @Value
* */

@EnableDiscoveryClient
@SpringBootApplication
public class GulimallCouponApplication {

	public static void main(String[] args) {
		SpringApplication.run(GulimallCouponApplication.class, args);
	}

}
