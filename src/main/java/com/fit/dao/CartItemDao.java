package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.CartItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 购物车接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface CartItemDao extends BaseCrudDao<CartItem> {
}