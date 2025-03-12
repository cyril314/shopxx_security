package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 导航
 * @Author AIM
 * @DATE 2025-03-05 14:13:47
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Navigation extends BaseEntity<Navigation> {
    /** 是否在新窗口中打开 (无默认值) */
    private Boolean isBlankTarget;

    /** 是否显示 (无默认值) */
    private Boolean isVisible;

    /** 名称 (无默认值) */
    private String name;

    /** 位置: top-0,middle-1,bottom-2 (无默认值) */
    private Integer position;

    /** 链接地址 (无默认值) */
    private String url;

    /** 排序 (无默认值) */
    private Integer sort;
}