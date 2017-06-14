package com.alone.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alone.web.dao.AdminPermissionDao;
import com.alone.web.dao.AdminUserDao;
import com.alone.web.entity.AdminPermission;
import com.alone.web.entity.AdminRole;
import com.alone.web.entity.AdminUser;
import com.alone.web.form.AdminUserForm;

@Service("adminUserService")
public class AdminUserService {

	@Autowired
	private AdminUserDao  adminUserDao;
	@Autowired
    private AdminPermissionDao adminPermissionDao;
    
	public AdminUser findAdminUserByUsername(String username) {
		AdminUser entity = new AdminUser();
		entity.setUsername(username);
		entity = adminUserDao.selectOne(entity);
		return entity;
	}

	public List<AdminPermission> findPermissionAllByUsername(String username) {
		//查询父级菜单
		List<AdminPermission> permissionList = adminUserDao.findPermissionAllByUsername(username);
		//查询子菜单
		AdminPermission child = new AdminPermission();
		for(AdminPermission parent : permissionList ){
			child.setParentId(parent.getId());
			List<AdminPermission> childList = adminPermissionDao.findChildPermission(parent.getId());
			parent.setChildPermission(childList);
		}
		return permissionList;
	}

	public void saveRegister(AdminUser adminUser) {
		adminUserDao.insert(adminUser);
	}

	public AdminRole findRoleByUsername(AdminUser adminUser) {
		AdminRole role = adminUserDao.findfindRoleByUsername(adminUser.getUsername());
		return role;
	}

	public AdminUserForm searchePage(AdminUserForm vo) {
		int total = adminUserDao.searchePageTotal(vo);
		if(total>0){
			List<AdminUserForm> rows = adminUserDao.searchePage(vo);
			vo.setRows(rows);
		}
		vo.setTotal(total);
		return vo;
	}

	
}
