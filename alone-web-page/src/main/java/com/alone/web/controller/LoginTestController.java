package com.alone.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginTestController {
	
	@RequestMapping("show")
	public String show(){
		Subject subject = SecurityUtils.getSubject();
		try {
			UsernamePasswordToken token = new UsernamePasswordToken("admin", "123456");
			subject.login(token);
			System.out.println("登录成功");
		} catch (Exception e) {
			System.out.println("登录失败");
			e.printStackTrace();
		}
		return "index/show";
	}
}
