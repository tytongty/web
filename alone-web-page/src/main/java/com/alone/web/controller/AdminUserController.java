package com.alone.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.form.AdminUserForm;
import com.alone.web.service.AdminUserService;
import com.alone.web.utils.PathConst;
@Controller
@RequestMapping("adminUser")
public class AdminUserController extends BasicController {
	@Autowired
	private AdminUserService adminUserService;
	@RequestMapping("/show")
	public ModelAndView show(HttpServletRequest request , AdminUserForm vo, ModelAndView mv){
		super.getMenuName(request);
		AdminUserForm page = adminUserService.searchePage(vo);
		mv.addObject("page", page);
		mv.setViewName(PathConst.BASE_PATH+"adminUser/show");
		return mv;
	}
	
	@RequestMapping("/searchPage")
	public ModelAndView searchPage(HttpServletRequest request , AdminUserForm vo, ModelAndView mv){
		AdminUserForm page = adminUserService.searchePage(vo);
		mv.addObject("page", page);
		mv.setViewName(PathConst.BASE_PATH+"adminUser/table");
		return mv;
	}
}
