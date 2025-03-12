package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 商品分类
 * @Author AIM
 * @DATE 2025-03-12 18:47:23
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class ProductCategory extends BaseEntity<ProductCategory> {
    /** 页面描述 (无默认值) */
    private String metaDescription;

    /** 页面关键词 (无默认值) */
    private String metaKeywords;

    /** 排序  (默认值为: 0) */
    private Integer sort;

    /** 分类名称 (无默认值) */
    private String name;

    /** 树路径 (无默认值) */
    private String path;

    /** 树层级  (默认值为: 0) */
    private Integer level;

    /** 上级分类  (默认值为: '0') */
    private String parentId;
}