package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.DeliveryCorp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 物流公司接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface DeliveryCorpDao extends BaseCrudDao<DeliveryCorp> {
}