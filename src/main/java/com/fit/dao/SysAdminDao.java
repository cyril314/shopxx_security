package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.SysAdmin;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 管理员接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:06
 */
@Mapper
public interface SysAdminDao extends BaseCrudDao<SysAdmin> {
}