package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.ProductTypeDao;
import com.fit.entity.ProductType;
import org.springframework.stereotype.Service;

/**
 * @AUTO 商品类型服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class ProductTypeService extends BaseCrudService<ProductTypeDao, ProductType> {
}