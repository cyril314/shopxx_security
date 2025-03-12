package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.DeliveryItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 物流项接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface DeliveryItemDao extends BaseCrudDao<DeliveryItem> {
}