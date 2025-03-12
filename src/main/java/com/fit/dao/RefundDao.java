package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.Refund;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 退款接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:06
 */
@Mapper
public interface RefundDao extends BaseCrudDao<Refund> {
}