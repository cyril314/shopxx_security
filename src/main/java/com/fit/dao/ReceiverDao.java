package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.Receiver;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 收货地址接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface ReceiverDao extends BaseCrudDao<Receiver> {
}