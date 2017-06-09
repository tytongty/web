package com.alone.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alone.web.entity.AdminPermission;
import com.alone.web.entity.AdminUser;
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

  List<AdminPermission> findPermissionAllByUsername(@Param("username")String username);

}