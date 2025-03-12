package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 日志配置
 * @Author AIM
 * @DATE 2025-03-11 15:30:34
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class LogConfig extends BaseEntity<LogConfig> {
    /** 需要进行日志记录的Action名称 (无默认值) */
    private String actionClassName;

    /** 需要进行日志记录的方法名称 (无默认值) */
    private String actionMethodName;

    /** 描述 (无默认值) */
    private String description;

    /** 操作名称 (无默认值) */
    private String operationName;
}