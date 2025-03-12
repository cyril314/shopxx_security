package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 订单项接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface OrderItemDao extends BaseCrudDao<OrderItem> {
}