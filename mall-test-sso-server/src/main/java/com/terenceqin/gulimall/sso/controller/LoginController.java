package com.terenceqin.gulimall.sso.controller;

import com.terenceqin.gulimall.sso.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: LoginController</p>
 * Description�? */
@Controller
public class LoginController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@ResponseBody
	@GetMapping("/userInfo") // 得到redis中的存储过的user信息，返回给子系统的session�?	public Object userInfo(@RequestParam("redisKey") String redisKey){
		// 拿着其他域名转发过来的token去redis里查
		Object loginUser = stringRedisTemplate.opsForValue().get(redisKey);
		return loginUser;
	}


	@GetMapping("/login.html") // 子系统都来这
	public String loginPage(@RequestParam("url") String url,
							Model model,
							@CookieValue(value = "redisKey", required = false) String redisKey) {
		// 非空代表就登录过�?		if (!StringUtils.isEmpty(redisKey)) {
			// 告诉子系统他的redisKey，拿着该token就可以查redis�?			return "redirect:" + url + "?redisKey=" + redisKey;
		}
		model.addAttribute("url", url);

		// 子系统都没登录过才去登录�?		return "login";
	}

	@PostMapping("/doLogin") // server端统一认证
	public String doLogin(@RequestParam("username") String username,
						  @RequestParam("password") String password,
						  HttpServletResponse response,
						  @RequestParam(value="url",required = false) String url){
		// 确认用户后，生成cookie、redis中存�?// if内代表取查完数据库了
		if(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)){//简单认为登录正�?			// 登录成功跳转 跳回之前的页�?			String redisKey = UUID.randomUUID().toString().replace("-", "");
			// 存储cookie�?是在server.com域名下存
			Cookie cookie = new Cookie("redisKey", redisKey);
			response.addCookie(cookie);
			// redis中存�?			stringRedisTemplate.opsForValue().set(redisKey, username+password+"...", 30, TimeUnit.MINUTES);
			// user中存储的url  重定向时候带着token
			return "redirect:" + url + "?redisKey=" + redisKey;
		}
		// 登录失败
		return "login";
	}

}
