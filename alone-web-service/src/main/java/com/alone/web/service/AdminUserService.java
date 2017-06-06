package com.alone.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alone.web.dao.AdminUserDao;
import com.alone.web.entity.AdminUser;

@Service("adminUserService")
public class AdminUserService {

	@Autowired
	private AdminUserDao  adminUserDao;

	public AdminUser findAdminUserByUsername(String username) {
		AdminUser entity = new AdminUser();
		entity.setUsername(username);
		entity = adminUserDao.selectOne(entity);
		return entity;
	}

	
}
