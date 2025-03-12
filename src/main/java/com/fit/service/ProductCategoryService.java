package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.ProductCategoryDao;
import com.fit.entity.ProductCategory;
import org.springframework.stereotype.Service;

/**
 * @AUTO 商品分类服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class ProductCategoryService extends BaseCrudService<ProductCategoryDao, ProductCategory> {
}