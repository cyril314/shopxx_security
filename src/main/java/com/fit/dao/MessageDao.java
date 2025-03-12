package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 消息接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface MessageDao extends BaseCrudDao<Message> {
}