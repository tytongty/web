package com.alone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alone.web.utils.PathConst;

@Controller
@RequestMapping("/manager")
public class Manager {

	@RequestMapping("case")
	public String show(){
		
		return PathConst.BASE_PATH+"manager/show";
	}
}
