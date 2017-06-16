package com.alone.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.form.MenuForm;
import com.alone.web.service.AdminPermissionService;
import com.alone.web.utils.PathConst;
@Controller
@RequestMapping("/menu")
public class MenuController extends BasicController {
	
	@Autowired
	private AdminPermissionService adminPermissionService;
	@RequestMapping("show")
	public ModelAndView show(ModelAndView mav,HttpServletRequest request ,MenuForm meuForm ){
		super.getMenuName(request);
		//只查询父菜单进行显示
		MenuForm page =	adminPermissionService.searchPage(meuForm);
		mav.addObject("page", page);

		mav.setViewName(PathConst.BASE_PATH+"menu/show");
		return mav;
	}
	
	@RequestMapping("findChildMenu")
	public ModelAndView findChildMenu(ModelAndView mav,String parentId,HttpServletRequest request){
		request.setAttribute("parentName", "权限管理");
		request.setAttribute("childName", "菜单管理");
		//查询子菜单进行显示
		MenuForm page =	adminPermissionService.findChildMenu(parentId);
		mav.addObject("page", page);
		mav.addObject("parentId", parentId);
		mav.setViewName(PathConst.BASE_PATH+"menu/show");
		return mav;
	}
	
	@RequestMapping("edit")
	public ModelAndView edit(ModelAndView mav,String parentId){
		mav.addObject("parentId", parentId);
		mav.setViewName(PathConst.BASE_PATH+"menu/addMenu");
		return mav;
	}
	
	@RequestMapping("saveMenu")
	public ModelAndView saveMenu(ModelAndView mav,MenuForm vo){
		try {
			adminPermissionService.saveMenu(vo);
			mav.addObject("msg", "添加成功");
		} catch (Exception e) {
			mav.addObject("msg", "添加失败");
			e.printStackTrace();
		}
		mav.setViewName(PathConst.BASE_PATH+"menu/addMenu");
		return mav;
	}
}
