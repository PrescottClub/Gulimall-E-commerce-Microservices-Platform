package com.terenceqin.gulimall.order.web;

import com.atguigu.common.exception.NotStockException;
import com.terenceqin.gulimall.order.service.OrderService;
import com.terenceqin.gulimall.order.vo.OrderConfirmVo;
import com.terenceqin.gulimall.order.vo.OrderSubmitVo;
import com.terenceqin.gulimall.order.vo.SubmitOrderResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.concurrent.ExecutionException;

/**
 * <p>Title: OrderController</p>
 * Description：订�? */
@Controller
@Slf4j
public class OrderWebController {

	@Autowired
	private OrderService orderService;

	// 购物车预备生成订单页�?	@GetMapping("/toTrade")
	public String toTrade(Model model) throws ExecutionException, InterruptedException {
		OrderConfirmVo confirmVo = orderService.confirmOrder();

		model.addAttribute("orderConfirmData", confirmVo);
		return "confirm";
	}

	/**
	 * 查看订单后提交订�?	 */
	@PostMapping("/submitOrder") // OrderWebController
	public String submitOrder(OrderSubmitVo submitVo, Model model, RedirectAttributes redirectAttributes){

		try {
			// 去OrderServiceImpl服务里验证和下单
			SubmitOrderResponseVo responseVo = orderService.submitOrder(submitVo);
			// 下单失败回到订单重新确认订单信息
			if(responseVo.getCode() == 0){
				// 下单成功取支付响�?				model.addAttribute("submitOrderResp", responseVo);
				// 支付�?				return "pay";
			}else{
				String msg = "下单失败";
				switch (responseVo.getCode()){ // 获取失败实现
					case 1: msg += "订单信息过期,请刷新在提交";break;
					case 2: msg += "订单商品价格发送变�?请确认后再次提交";break;
					case 3: msg += "商品库存不足";break;
				}
				redirectAttributes.addFlashAttribute("msg", msg);
				// 重定�?				return "redirect:http://order.gulimall.com/toTrade";
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.warn(e.getMessage());
			if (e instanceof NotStockException){
				String message = e.getMessage();
				redirectAttributes.addFlashAttribute("msg", message);
			}
			return "redirect:http://order.gulimall.com/toTrade";
		}
	}
}
