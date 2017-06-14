package com.alone.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.utils.PathConst;

@Controller
@RequestMapping("/manager")
public class Manager extends BasicController {

	@RequestMapping("case")
	public String show(HttpServletRequest request){
		super.getMenuName(request);
		return PathConst.BASE_PATH+"manager/show";
	}
	@RequestMapping("case2")
	public String show2(HttpServletRequest request){
		super.getMenuName(request);
		return PathConst.BASE_PATH+"manager/show";
	}
}
