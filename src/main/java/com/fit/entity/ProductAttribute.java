package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 商品属性
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class ProductAttribute extends BaseEntity<ProductAttribute> {
    /** 可选项储存 (无默认值) */
    private String attributeoptionstore;

    /** 商品类型 (无默认值) */
    private Integer attributetype;

    /** 是否必填 (无默认值) */
    private Boolean isrequired;

    /** 是否启用 (无默认值) */
    private Boolean isenabled;

    /** 属性名称 (无默认值) */
    private String name;

    /** 排序 (无默认值) */
    private Integer sort;

    /**  (无默认值) */
    private String productTypeId;
}