package com.alone.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.entity.AdminRole;
import com.alone.web.service.AdminPermissionService;
import com.alone.web.utils.AjaxBean;
import com.alone.web.utils.NodeTree;
import com.alone.web.utils.PathConst;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
	
	@RequestMapping("getNodeList")
	@ResponseBody
	public  List<NodeTree>  getNodeList (String roleId){
		//查出所有权限
	 List<NodeTree> nodeTreeList = adminPermissionService.getNodeList(roleId);
	  return nodeTreeList;
	}
	
	@RequestMapping("edit")
	@ResponseBody
	public  AjaxBean edit (AjaxBean ajaxBean,String nodeTree,String roleId){
          Gson gson = new Gson();
          List<NodeTree> trees=  gson.fromJson(nodeTree, new  TypeToken<List<NodeTree>>(){}.getType());
          try {
			adminPermissionService.editPermission(trees, roleId);
			ajaxBean.setMsg("修改成功");
		} catch (Exception e) {
			ajaxBean.setMsg("修改失败");
			e.printStackTrace();
		}
          
	  return ajaxBean;
	}
}
