package com.terenceqin.gulimall.seckill.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;



/**
 * <p>Title: HelloSchedule</p>
 * Description：@Scheduled它不是整合cron ,如果要整合cron 需要自己引�? * date�?020/7/6 17:03
 */
@Slf4j
// 开启异步任�?//@EnableAsync
//@EnableScheduling
//@Component
public class HelloSchedule {

	/**
	 * 在Spring�?只允�?�?[* * * ? * 1] : 每周一每秒执行一�?	 * 						[* /5 * * ? * 1] : 每周一 �?秒执行一�?	 * 	方法1.定时任务不应阻塞 [默认是阻塞的]
	 * 	方法2.定时任务线程�?spring.task.scheduling.pool.size=5  自动配置�?	 * 	方法3.让定时任务异步执�?  自动配置�?TaskExecutionAutoConfiguration
	 */
	@Async
	@Scheduled(cron = "*/5 * * ? * 1")
	public void hello(){
		log.info("定时任务...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) { }
	}
}
