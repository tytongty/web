package com.alone.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.entity.AdminPermission;
import com.alone.web.entity.AdminRole;
import com.alone.web.entity.AdminUser;
import com.alone.web.service.AdminUserService;
import com.alone.web.utils.Crypto;
import com.alone.web.utils.PathConst;

@Controller
@RequestMapping("/login")
public class LoginController extends BasicController {
	@Autowired
	private AdminUserService adminUserService;

	/**
	 * 
	 * 登录页面显示
	 * 
	 * @return
	 */
	@RequestMapping("show")
	public String show() {
		
		return PathConst.BASE_PATH + "login/index";
	}

	/**
	 * 页面登录
	 * 
	 * @param mv
	 * @param adminUser
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(ModelAndView mv, AdminUser adminUser, HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			mv.setViewName(PathConst.BASE_PATH + "index/index");
			return mv;
		}
		UsernamePasswordToken token = new UsernamePasswordToken(adminUser.getUsername(), adminUser.getPassword());
		try {
			subject.login(token);
			mv.addObject("msg", "登录成功");
			mv.setViewName(PathConst.BASE_PATH + "index/index");
			// 根据用户名查询所有权限
			List<AdminPermission> parentPermissionList = adminUserService
					.findPermissionAllByUsername(adminUser.getUsername());
			//mv.addObject("parentPermissionList", parentPermissionList);
			request.getSession().setAttribute("parentPermissionList", parentPermissionList);
			//查出其角色名称
			AdminRole role =adminUserService.findRoleByUsername(adminUser);
			request.getSession().setAttribute("role",role);

		} catch (AuthenticationException e) {
			mv.addObject("msg", "登录失败");
			mv.setViewName(PathConst.BASE_PATH + "login/fail");
			adminUser.setUsername("");
			e.printStackTrace();
		}
		request.getSession().setAttribute("user", adminUser);
		return mv;
	}

	/**
	 * 注册页面显示
	 * 
	 * @param mv
	 * @param adminUser
	 * @return
	 */
	@RequestMapping("register")
	public ModelAndView register(ModelAndView mv, AdminUser adminUser) {
		mv.setViewName(PathConst.BASE_PATH + "login/register");
		return mv;
	}

	/**
	 * （游客）保存注册信息 
	 * 
	 * @param mv
	 * @param adminUser
	 * @return
	 */
	@RequestMapping("registerSave")
	public ModelAndView registerSave(ModelAndView mv, AdminUser adminUser) {
		try {
			// 注册成功
			String oldPassword = adminUser.getPassword();
			// 密码加密
			String newPassword = Crypto.cryptoPassword(oldPassword, adminUser.getUsername());
			ByteSource salt = Crypto.cryptoSalt(adminUser.getUsername());
			String id = Crypto.getUUID();
			adminUser.setPassword(newPassword);
			adminUser.setSalt(salt.toString());
			adminUser.setUserId(id);
			adminUser.setRoleId("super");
			adminUserService.saveRegister(adminUser);
			mv.setViewName(PathConst.BASE_PATH + "login/index");
		} catch (Exception e) {
			// 注册失败
			mv.setViewName(PathConst.BASE_PATH + "login/fail");
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 退出 并转到登录页面
	 * 
	 * @param mv
	 * @return
	 */
	@RequestMapping("loginOut")
	public ModelAndView loginOut(ModelAndView mv) {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		mv.setViewName(PathConst.BASE_PATH + "login/index");
		return mv;
	}
}
