package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 品牌
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Brand extends BaseEntity<Brand> {
    /** 介绍 (无默认值) */
    private String introduction;

    /** 商标 (无默认值) */
    private String logo;

    /** 名称 (无默认值) */
    private String name;

    /** 网址 (无默认值) */
    private String url;

    /** 排序 (无默认值) */
    private Integer sort;
}