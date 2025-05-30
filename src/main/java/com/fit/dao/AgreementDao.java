package com.fit.dao;

import com.fit.base.BaseCrudDao;
import com.fit.entity.Agreement;
import org.apache.ibatis.annotations.Mapper;

/**
 * @AUTO 会员注册协议接口
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Mapper
public interface AgreementDao extends BaseCrudDao<Agreement> {
}