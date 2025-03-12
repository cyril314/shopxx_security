package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 角色
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class SysRole extends BaseEntity<SysRole> {
    /** 描述 (无默认值) */
    private String description;

    /** 是否为系统内置角色 (无默认值) */
    private Boolean iSys;

    /** 角色名称 (无默认值) */
    private String name;

    /** 角色标识 (无默认值) */
    private String value;
}