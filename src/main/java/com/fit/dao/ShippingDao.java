package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.Shipping;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 发货接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:06
 */
@Mapper
public interface ShippingDao extends BaseCrudDao<Shipping> {
}