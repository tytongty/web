
package com.alone.web.basic.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.alone.web.entity.AdminPermission;

public class BasicController {
  
	private static Logger logger = Logger.getLogger(BasicController.class);
	//根据menu 点击的请求地址 获取菜单兰 的父级菜单和子菜单
	public void getMenuName(HttpServletRequest  request) {
	@SuppressWarnings("unchecked")
	List<AdminPermission>  parentList =	(List<AdminPermission>) request.getSession().getAttribute("parentPermissionList");
            // /alone/login/show
	String reqUri = request.getRequestURI();
	reqUri = reqUri.substring(1);
	reqUri = reqUri.substring(reqUri.indexOf("/")+1);
	logger.info(reqUri);
	for(AdminPermission parent : parentList){
		for(AdminPermission child : parent.getChildPermission()){
			if(child.getUrl().contains(reqUri)){
				request.setAttribute("parentName", parent.getName());
				request.setAttribute("childName", child.getName());
				break;
			}
		}
		
	}
	}
}
