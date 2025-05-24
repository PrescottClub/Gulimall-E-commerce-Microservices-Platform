package com.terenceqin.gulimall.malltestssoclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: HelloController</p>
 * Description�? * date�?020/6/27 15:00
 */
@Controller
public class HelloController {

	@Value("${sso.server.url}")
	private String ssoServer;

	/*** 无需登录就可访问*/
	@ResponseBody
	@GetMapping(value = "/hello")
	public String hello(HttpServletRequest request) {
		System.out.println(request.getRequestURI());
		System.out.println(request.getRequestURL());
		return "hello"; }


	@GetMapping(value = "/employees") // a系统
	public String employees(Model model,
							HttpSession session,
							@RequestParam(value = "redisKey", required = false) String redisKey) {

		// 有loginToken这个参数，代表去过server端登录过了，server端里在redis里保存了个对象，而key:uuid给你发过来了
		// 有loginToken这个参数的话代表是从登录页跳回来的，而不是系统a直接传过来的
		// 你再拿着uuid再去查一遍user object，返回后设置到当前的系统session�?		// 提个问题：为什么当时不直接返回user对象，而是只返回个uuid？其实也可以，但是参数的地方也得是required = false。可能也有一些安全问�?		if (!StringUtils.isEmpty(redisKey)) { // 这个逻辑应该写到过滤器或拦截器里
			RestTemplate restTemplate=new RestTemplate();
			// 拿着token去服务器，在服务端从redis中查出来他的username
			ResponseEntity<Object> forEntity =
					restTemplate.getForEntity("http://ssoserver.com:8080/userInfo?redisKey="+ redisKey, Object.class);

			Object loginUser = forEntity.getBody();
			session.setAttribute("loginUser", loginUser);
		}
		// session里有就代表登录过 // 获得user
		Object loginUser = session.getAttribute("loginUser");

		if (loginUser == null) { // 又没有loginToken，session里又没有object，去登录页登�?			return "redirect:" + "http://ssoserver.com:8080/login.html"
					+ "?url=http://clientA.com/employees";
		} else {// 登录过，执行正常的业�?			List<String> emps = new ArrayList<>();

			emps.add("张三");
			emps.add("李四");
			model.addAttribute("emps", emps);
			return "employees";
		}
	}

}

