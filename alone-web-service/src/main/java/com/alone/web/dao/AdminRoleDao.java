package com.alone.web.dao;

import java.util.List;

import com.alone.web.entity.AdminRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author 程新井
 * @since 2017-06-05
 */
public interface AdminRoleDao extends BaseMapper<AdminRole> {

	List<AdminRole> findAllRole(AdminRole adminRole);

}