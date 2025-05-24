package com.terenceqin.gulimall.order.listener;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.terenceqin.gulimall.order.config.AlipayTemplate;
import com.terenceqin.gulimall.order.service.OrderService;
import com.terenceqin.gulimall.order.vo.PayAsyncVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>Title: OrderPayedListener</p>
 * Description�? * date�?020/7/5 10:54
 */
@Slf4j
@RestController
public class OrderPayedListener {

	@Autowired
	private OrderService orderService;

	@Autowired
	private AlipayTemplate alipayTemplate;

	@PostMapping("/payed/notify")
	public String handleAliPayed(PayAsyncVo vo, HttpServletRequest request) throws UnsupportedEncodingException, AlipayApiException {
		log.info("\n收到支付宝最后的通知数据：\n" + vo);
//		Map<String, String[]> result = request.getParameterMap();
//		String map = "";
//		for (String key : result.keySet()) {
//			map += key + "-->" + request.getParameter(key) + "\n";
//		}
		// 验签
		Map<String,String> params = new HashMap<>();
		Map<String,String[]> requestParams = request.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = iter.next();
			String[] values = requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i]
						: valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使�?//			valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		// 只要我们收到了支付宝给我们的异步通知 验签成功 我们就要给支付宝返回success
		if(AlipaySignature.rsaCheckV1(params, alipayTemplate.getAlipay_public_key(), alipayTemplate.getCharset(), alipayTemplate.getSign_type())){
			return orderService.handlePayResult(vo);
		}
		log.warn("\n受到恶意验签攻击");
		return "fail";
	}
}
