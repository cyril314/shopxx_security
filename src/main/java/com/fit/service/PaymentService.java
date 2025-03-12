package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.PaymentDao;
import com.fit.entity.Payment;
import org.springframework.stereotype.Service;

/**
 * @AUTO 支付服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class PaymentService extends BaseCrudService<PaymentDao, Payment> {
}