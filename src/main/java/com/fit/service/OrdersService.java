package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.OrdersDao;
import com.fit.entity.Orders;
import org.springframework.stereotype.Service;

/**
 * @AUTO 订单服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class OrdersService extends BaseCrudService<OrdersDao, Orders> {
}