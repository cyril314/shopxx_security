package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 支付配置
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class PaymentConfig extends BaseEntity<PaymentConfig> {
    /** 配置对象信息储存 (无默认值) */
    private String configobjectstore;

    /** 介绍 (无默认值) */
    private String description;

    /** 支付方式名称 (无默认值) */
    private String name;

    /** 排序 (无默认值) */
    private Integer sort;

    /** 支付费用 (无默认值) */
    private BigDecimal paymentfee;

    /** 支付配置类型 (无默认值) */
    private Integer paymentconfigtype;

    /** 支付手续费类型 (无默认值) */
    private Integer paymentfeetype;
}