package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.OrderLogDao;
import com.fit.entity.OrderLog;
import org.springframework.stereotype.Service;

/**
 * @AUTO 订单日志服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class OrderLogService extends BaseCrudService<OrderLogDao, OrderLog> {
}