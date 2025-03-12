package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.CartItemDao;
import com.fit.entity.CartItem;
import org.springframework.stereotype.Service;

/**
 * @AUTO 购物车服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class CartItemService extends BaseCrudService<CartItemDao, CartItem> {
}