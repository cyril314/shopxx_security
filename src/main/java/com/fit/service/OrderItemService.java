package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.OrderItemDao;
import com.fit.entity.OrderItem;
import org.springframework.stereotype.Service;

/**
 * @AUTO 订单项服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class OrderItemService extends BaseCrudService<OrderItemDao, OrderItem> {
}