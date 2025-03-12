package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.LogConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 日志配置接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface LogConfigDao extends BaseCrudDao<LogConfig> {
}