package com.fit.service;

import com.fit.base.BaseCrudService;
import com.fit.dao.ProductDao;
import com.fit.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @AUTO 商品服务实现类
 * @Author AIM
 * @DATE 2025-01-09 16:45:05
 */
@Service
public class ProductService extends BaseCrudService<ProductDao, Product> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getNewProductList(String product_category_id) {
        String sql = "SELECT * FROM `product` p LEFT JOIN `product_category` c ON p.`product_category_id`=c.`id` WHERE p.`is_marketable`=1 AND p.`is_new` AND (p.`product_category_id`=? OR c.`path` LIKE ?) ORDER BY p.`create_time` DESC";
        return jdbcTemplate.queryForList(sql, new Object[]{product_category_id, product_category_id + "%"});
    }
}