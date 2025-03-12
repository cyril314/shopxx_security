package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 角色接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:06
 */
@Mapper
public interface SysRoleDao extends BaseCrudDao<SysRole> {
}