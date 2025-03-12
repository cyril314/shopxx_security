package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 订单日志
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class OrderLog extends BaseEntity<OrderLog> {
    /** 操作员 (无默认值) */
    private String operator;

    /** 日志信息 (无默认值) */
    private String info;

    /** 订单日志类型 (无默认值) */
    private Integer orderlogtype;

    /** 订单编号 (无默认值) */
    private String ordersn;

    /** 订单ID (无默认值) */
    private String orderId;
}