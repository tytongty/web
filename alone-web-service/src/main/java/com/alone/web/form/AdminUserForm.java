package com.alone.web.form;

import com.alone.web.utils.BasicPage;
/**
 * 用户表
 * @author 程新井
 *
 */
public class AdminUserForm extends BasicPage<AdminUserForm>  {
	
	private String roleId;
	private String username;
	private String userId;
	private Integer locked;
	//是否分页 （否：0）
	private String isPage;
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getLocked() {
		return locked;
	}

	public void setLocked(Integer locked) {
		this.locked = locked;
	}

	public String getIsPage() {
		return isPage;
	}

	public void setIsPage(String isPage) {
		this.isPage = isPage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
