package com.terenceqin.gulimall.product.interceptor;

import com.atguigu.common.constant.AuthServerConstant;
import com.atguigu.common.vo.MemberRespVo;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <p>Title: LoginUserInterceptor</p>
 * Description�? * date�?020/7/4 22:35
 */
@Component
public class LoginUserInterceptor implements HandlerInterceptor {

	public static ThreadLocal<MemberRespVo> threadLocal = new ThreadLocal<>();

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = request.getRequestURI();
		// 这个请求直接放行
		boolean match = new AntPathMatcher().match("/member/**", uri);
		if(match){
			return true;
		}
		// 去会话里找用户，如果有用户则设置到threadlocal�?		HttpSession session = request.getSession();
		MemberRespVo MemberRespVo = (MemberRespVo) session.getAttribute(AuthServerConstant.LOGIN_USER);
		if(MemberRespVo != null){
			threadLocal.set(MemberRespVo);
			return true;
		}else{
			// 没登陆就去登�?			session.setAttribute("msg", AuthServerConstant.NOT_LOGIN);
			response.sendRedirect("http://auth.gulimall.com/login.html");
			return false;
		}
	}
}
