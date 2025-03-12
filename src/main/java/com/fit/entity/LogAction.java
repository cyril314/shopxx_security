package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 
 * @Author AIM
 * @DATE 2025-03-11 15:30:34
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class LogAction extends BaseEntity<LogAction> {
    /** 操作类名 (无默认值) */
    private String actionClassName;

    /** 操作方法名 (无默认值) */
    private String actionMethodName;

    /**  (无默认值) */
    private String ip;

    /** 日志信息 (无默认值) */
    private String info;

    /** 操作名称 (无默认值) */
    private String operation;

    /** 操作员 (无默认值) */
    private String operator;
}