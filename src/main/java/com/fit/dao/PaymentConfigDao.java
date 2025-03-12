package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.PaymentConfig;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 支付配置接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface PaymentConfigDao extends BaseCrudDao<PaymentConfig> {
}