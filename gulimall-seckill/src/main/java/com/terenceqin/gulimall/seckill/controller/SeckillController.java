package com.terenceqin.gulimall.seckill.controller;

import com.atguigu.common.utils.R;
import com.terenceqin.gulimall.seckill.service.SeckillService;
import com.atguigu.common.to.SeckillSkuRedisTo;
import  org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>Title: SeckillController</p>
 */
@Controller
public class SeckillController {

	@Autowired
	private SeckillService seckillService;

	@ResponseBody
	@GetMapping("/currentSeckillSkus")
	public R getCurrentSeckillSkus(){
		List<SeckillSkuRedisTo> vos = seckillService.getCurrentSeckillSkus();
		return R.ok().setData(vos);
	}

	@ResponseBody
	@GetMapping("/sku/seckill/{skuId}")
	public R getSkuSeckillInfo(@PathVariable("skuId") Long skuId){
		SeckillSkuRedisTo to = seckillService.getSkuSeckillInfo(skuId);
		return R.ok().setData(to);
	}

	@GetMapping("/kill")
	public String secKill(@RequestParam("killId") String killId, // session_skuID
						  @RequestParam("key") String key,
						  @RequestParam("num") Integer num, Model model){
		String orderSn = seckillService.kill(killId,key,num);
		// 1.判断是否登录
		model.addAttribute("orderSn", orderSn);
		return "success";
	}
}
