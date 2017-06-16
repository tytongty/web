package com.alone.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alone.web.basic.controller.BasicController;
import com.alone.web.entity.AdminUser;
import com.alone.web.form.AdminUserForm;
import com.alone.web.service.AdminUserService;
import com.alone.web.utils.AjaxBean;
import com.alone.web.utils.Crypto;
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
	
	@RequestMapping("/edit")
	public ModelAndView edit( String userId , ModelAndView mv){
		if(!StringUtils.isEmpty(userId)){
			//用户编辑
			//根据userId查询该用户
			AdminUser user = adminUserService.findAdminUserById(userId);
			mv.addObject("userEdit", user);
		}
		mv.addObject("userId", userId);
		mv.setViewName(PathConst.BASE_PATH+"adminUser/edit");
		return mv;
	}
	
	@RequestMapping("/saveOrUpdate")
	public ModelAndView saveOrUpdate( AdminUserForm vo , ModelAndView mv){
		AdminUser user = new AdminUser();
		if(StringUtils.isEmpty(vo.getUserId())){
			//添加用户
			user.setUsername(vo.getUsername());
			//密码加密
			String password = Crypto.cryptoPassword(vo.getPassword(), vo.getUsername());
			//盐加密
			String salt = Crypto.cryptoSalt(vo.getUsername()).toString();
			String userId = Crypto.getUUID();
			user.setPassword(password);
			user.setLocked(0);
			user.setSalt(salt);
			user.setUserId(userId);
			user.setRoleId("super");
			adminUserService.saveRegister(user);
			mv.addObject("msg", "添加成功");
		}else{
			adminUserService.saveEdit(vo);
			mv.addObject("msg", "修改成功");
		}
		mv.setViewName(PathConst.BASE_PATH+"adminUser/edit");
		return mv;
	}
	

	@RequestMapping("/delete")
	@ResponseBody
	public AjaxBean delete( String userId ,AjaxBean ajaxBean){
		try {
			adminUserService.delete(userId);
			ajaxBean.isSuccess(1);
			ajaxBean.setMsg("删除成功");
		} catch (Exception e) {
			ajaxBean.setMsg("删除失败");
			e.printStackTrace();
		}
		return ajaxBean;
	}
}
