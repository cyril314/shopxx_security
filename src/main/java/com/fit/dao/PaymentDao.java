package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 支付接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface PaymentDao extends BaseCrudDao<Payment> {
}