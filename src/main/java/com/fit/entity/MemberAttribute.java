package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 会员注册项
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class MemberAttribute extends BaseEntity<MemberAttribute> {
    /** 可选项储存 (无默认值) */
    private String attributeOption;

    /** 注册项类型 (无默认值) */
    private Integer attributeType;

    /** 是否启用 (无默认值) */
    private Boolean isEnabled;

    /** 是否必填 (无默认值) */
    private Boolean isRequired;

    /** 注册项名称 (无默认值) */
    private String name;

    /** 排序 (无默认值) */
    private Integer sort;
}