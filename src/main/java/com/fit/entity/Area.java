package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 地区
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Area extends BaseEntity<Area> {
    /** 排序 (无默认值) */
    private Integer sort;

    /** 地区名称 (无默认值) */
    private String name;

    /** 地区路径 (无默认值) */
    private String path;

    /** 上级地区 (无默认值) */
    private String parentId;
}