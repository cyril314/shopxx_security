package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.DeliveryType;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 配送方式接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface DeliveryTypeDao extends BaseCrudDao<DeliveryType> {
}