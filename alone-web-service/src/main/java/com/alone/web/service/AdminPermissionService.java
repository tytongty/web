package com.alone.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alone.web.dao.AdminPermissionDao;
import com.alone.web.dao.AdminRoleDao;
import com.alone.web.entity.AdminPermission;
import com.alone.web.entity.AdminRole;
import com.alone.web.form.MenuForm;

/**
 * 菜单服务类
 * 
 * @author 程新井
 *
 */
@Service("adminPermissionService")
public class AdminPermissionService {
	@Autowired
	private AdminPermissionDao adminPermissionDao;
	@Autowired
	private AdminRoleDao adminRoleDao;
	public MenuForm searchPage(MenuForm meuForm) {
		//只查询父菜单
	    int total =	adminPermissionDao.searchParentPageTotal(meuForm);
		if (total > 0) {
			List<MenuForm> rows = adminPermissionDao.searchParentPage(meuForm);
			meuForm.setRows(rows);
		}
		meuForm.setTotal(total);
		return meuForm;
	}
	
	/**
	 * 根据父级菜单id查询子菜单
	 * @param id
	 * @return
	 */
	public MenuForm findChildMenu(String id) {
		MenuForm menuForm = new MenuForm();
		menuForm.setParentId(id);
		menuForm = searchPage(menuForm);
		return menuForm;
	}
	
	/**
	 * 保存菜单
	 * @param vo
	 */
	public void saveMenu(MenuForm vo) {
		AdminPermission entity = new AdminPermission();
		entity.setId(vo.getId());
		entity.setName(vo.getName());
		entity.setParentId(vo.getParentId());
		entity.setSort(vo.getSort());
		entity.setUrl(vo.getUrl());
		adminPermissionDao.insert(entity);
	}
	
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<AdminRole> getRole(AdminRole role) {
		List<AdminRole> roleList = adminRoleDao.findAllRole(role);
		return roleList;
	}

}
