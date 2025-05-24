package com.terenceqin.gulimall.seckill.schedule;

import com.terenceqin.gulimall.seckill.service.SeckillService;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * <p>Title: SeckillSkuScheduled</p>
 * Description：秒杀商品定时上架		[秒杀的定时任务调度]
 * date�?020/7/6 17:28
 */
@Slf4j
@Service
public class SeckillSkuScheduled {

	@Autowired
	private SeckillService seckillService;

	@Autowired
	private RedissonClient redissonClient;

	private final String upload_lock = "seckill:upload:lock";
	/**
	 * 这里应该是幂等的
	 *  三秒执行一次：* /3 * * * * ?
	 *  8小时执行一次：0 0 0-8 * * ?
	 */
	@Scheduled(cron = "0 0 0-8 * * ?")
	public void uploadSeckillSkuLatest3Day(){
		log.info("\n上架秒杀商品的信�?);
		// 1.重复上架无需处理 加上分布式锁 状态已经更�?释放锁以后其他人才获取到最新状�?		RLock lock = redissonClient.getLock(upload_lock);// "seckill:upload:lock";
		lock.lock(10, TimeUnit.SECONDS);
		try {
			seckillService.uploadSeckillSkuLatest3Day();
		} finally {
			lock.unlock();
		}
	}
}
