package com.auguigu.gulimall.auth.controller;

import com.alibaba.fastjson.TypeReference;
import com.atguigu.common.constant.AuthServerConstant;
import com.atguigu.common.exception.BizCodeEnum;
import com.atguigu.common.utils.R;
import com.atguigu.common.vo.MemberRespVo;
import com.auguigu.gulimall.auth.feign.MemberFeignService;
import com.auguigu.gulimall.auth.feign.ThirdPartFeignService;
import com.auguigu.gulimall.auth.vo.UserLoginVo;
import com.auguigu.gulimall.auth.vo.UserRegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>Title: LoginController</p>
 * Description：登录注册模�? * date�?020/6/25 13:02
 */
@Slf4j
@Controller
public class LoginController {

	@Autowired
	private ThirdPartFeignService thirdPartFeignService;

	@Autowired
	private MemberFeignService memberFeignService;

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@GetMapping({"/login.html","/","/index","/index.html"}) // auth
	public String loginPage(HttpSession session){
		// 从会话从获取loginUser
		Object attribute = session.getAttribute(AuthServerConstant.LOGIN_USER);// "loginUser";
		System.out.println("attribute:"+attribute);
		if(attribute == null){
			return "login";
		}
		System.out.println("已登陆过，重定向到首�?);
		return "redirect:http://gulimall.com";
	}

	@GetMapping("/test") // auth
	@ResponseBody
	public R login1() {
		UserLoginVo userLoginVo = new UserLoginVo();
		userLoginVo.setLoginacct("test");
		userLoginVo.setPassword("123456");
		R r = memberFeignService.login(userLoginVo);
		return r;

	}
	@PostMapping("/login") // auth
	public String login(UserLoginVo userLoginVo, // from表单里带过来�?						RedirectAttributes redirectAttributes,
						HttpSession session){
		// 远程登录
		R r = memberFeignService.login(userLoginVo);
		if(r.getCode() == 0){
			// 登录成功
			MemberRespVo respVo = r.getData("data", new TypeReference<MemberRespVo>() {});
			// 放入session
			session.setAttribute(AuthServerConstant.LOGIN_USER, respVo);//loginUser
			log.info("\n欢迎 [" + respVo.getUsername() + "] 登录");
			return "redirect:http://gulimall.com";
		}else {
			HashMap<String, String> error = new HashMap<>();
			// 获取错误信息
			error.put("msg", r.getData("msg",new TypeReference<String>(){}));
			redirectAttributes.addFlashAttribute("errors", error);
			return "redirect:http://auth.gulimall.com/login.html";
		}
	}

	/** 接收到一个手机号，在此处生成验证码和缓存，然后转给第三方服务让他给手机发验证�?	 * */
	@ResponseBody
	@GetMapping("/sms/sendcode")
	public R sendCode(@RequestParam("phone") String phone){

		//  TODO 接口防刷(冷却时长递增)，redis缓存 sms:code:电话�?		String redisCode = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone);
		// 如果不为空，返回错误信息
		if(null != redisCode && redisCode.length() > 0){
			long CuuTime = Long.parseLong(redisCode.split("_")[1]);
			if(System.currentTimeMillis() - CuuTime < 60 * 1000){ // 60s
				return R.error(BizCodeEnum.SMS_CODE_EXCEPTION.getCode(), BizCodeEnum.SMS_CODE_EXCEPTION.getMsg());
			}
		}
		// 生成验证�?		String code = UUID.randomUUID().toString().substring(0, 6);
		String redis_code = code + "_" + System.currentTimeMillis();
		// 缓存验证�?		stringRedisTemplate.opsForValue().set(AuthServerConstant.SMS_CODE_CACHE_PREFIX + phone, redis_code , 10, TimeUnit.MINUTES);
		try {// 调用第三方短信服�?			return thirdPartFeignService.sendCode(phone, code);
		} catch (Exception e) {
			log.warn("远程调用不知名错�?[无需解决]");
		}
		return R.ok();
	}

	/**
	 * TODO 重定向携带数�?利用session原理 将数据放在sessoin�?取一次之后删�?	 *
	 * TODO 1. 分布式下的session问题
	 * 校验
	 * RedirectAttributes redirectAttributes �?模拟重定向带上数�?	 */
	@PostMapping("/register")
	public String register(@Valid UserRegisterVo userRegisterVo,
						   BindingResult result,
						   RedirectAttributes redirectAttributes){

		if(result.hasErrors()){

			// 将错误属性与错误信息一一封装
			Map<String, String> errors = result.getFieldErrors().stream().collect(
					Collectors.toMap(FieldError::getField, fieldError -> fieldError.getDefaultMessage()));
			// addFlashAttribute 这个数据只取一�?			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:http://auth.gulimall.com/reg.html";
		}
		// 开始注�?调用远程服务
		// 1.校验验证�?		String code = userRegisterVo.getCode();

		String redis_code = stringRedisTemplate.opsForValue().get(AuthServerConstant.SMS_CODE_CACHE_PREFIX + userRegisterVo.getPhone());
		if(!StringUtils.isEmpty(redis_code)){
			// 验证码通过
			if(code.equals(redis_code.split("_")[0])){
				// 删除验证�?				stringRedisTemplate.delete(AuthServerConstant.SMS_CODE_CACHE_PREFIX + userRegisterVo.getPhone());
				// 调用远程服务进行注册
				R r = memberFeignService.register(userRegisterVo);
				if(r.getCode() == 0){
					// 注册成功，去登录
					return "redirect:http://auth.gulimall.com/login.html";
				}else{
					Map<String, String> errors = new HashMap<>();
					errors.put("msg",r.getData("msg",new TypeReference<String>(){}));
					// 数据只需要取一�?					redirectAttributes.addFlashAttribute("errors",errors);
					return "redirect:http://auth.gulimall.com/reg.html";
				}
			}else{
				Map<String, String> errors = new HashMap<>();
				errors.put("code", "验证码错�?);
				// addFlashAttribute 这个数据只取一�?				redirectAttributes.addFlashAttribute("errors", errors);
				return "redirect:http://auth.gulimall.com/reg.html";
			}
		}else{
			Map<String, String> errors = new HashMap<>();
			errors.put("code", "验证码错�?);
			// addFlashAttribute 这个数据只取一�?			redirectAttributes.addFlashAttribute("errors", errors);
			return "redirect:http://auth.gulimall.com/reg.html";
		}
	}
}
