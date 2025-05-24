package com.terenceqin.gulimall.seckill.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.fastjson.JSON;
import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>Title: SecKillSentinelConfig</p>
 * Description：配置请求被限制以后的处理器
 * date�?020/7/10 13:47
 */
@Configuration
public class SecKillSentinelConfig {

	public SecKillSentinelConfig(){
		WebCallbackManager.setUrlBlockHandler((request, response, exception) -> {
			R error = R.error(BizCodeEnum.TO_MANY_REQUEST.getCode(), BizCodeEnum.TO_MANY_REQUEST.getMsg());
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			response.getWriter().write(JSON.toJSONString(error));
		});
	}
}
//@Component
//public class GulimallSentinelConfig
//		implements UrlBlockHandler{
//	@Override
//	public void blocked(HttpServletRequest request,
//						HttpServletResponse response,
//						BlockException ex) throws IOException {
//		R r = R.error(BizCodeEnum.SECKILL_EXCEPTION.getCode(),BizCodeEnum.SECKILL_EXCEPTION.getMsg());
//		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().write(JSON.toJSONString(r)+"servlet用法");
//	}
//}
