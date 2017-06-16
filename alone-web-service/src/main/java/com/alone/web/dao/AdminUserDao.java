package com.alone.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alone.web.entity.AdminPermission;
import com.alone.web.entity.AdminRole;
import com.alone.web.entity.AdminUser;
import com.alone.web.form.AdminUserForm;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 程新井
 * @since 2017-06-05
 */
public interface AdminUserDao extends BaseMapper<AdminUser> {

	List<AdminPermission> findPermissionAllParentByUsername(@Param("username") String username);

	AdminRole findfindRoleByUsername(@Param("username") String username);

	int searchePageTotal(AdminUserForm vo);

	List<AdminUserForm> findEntityByEntity(AdminUserForm vo);

	List<AdminUserForm> searchePage(AdminUserForm vo);

	List<AdminPermission> findPermissionAllChildByUsername(String username);

}