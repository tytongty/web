package com.alone.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.entity.AdminRole;
import com.alone.web.service.AdminPermissionService;
import com.alone.web.utils.PathConst;

@Controller
@RequestMapping("permission")
public class AdminPermissionController extends BasicController {
	
	@Autowired
	private AdminPermissionService adminPermissionService;
	@RequestMapping("show")
	public ModelAndView show (HttpServletRequest request ,ModelAndView mav){
		super.getMenuName(request);
		mav.setViewName(PathConst.BASE_PATH+"permission/show");
		return mav;
	}
	
	@RequestMapping("getRole")
	@ResponseBody
	public  List<AdminRole>  getRole (ModelAndView mav,AdminRole role){
	  List<AdminRole> roleList = adminPermissionService.getRole(role);
	  return roleList;
	}
}
