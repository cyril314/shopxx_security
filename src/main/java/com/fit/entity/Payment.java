package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 支付
 * @Author AIM
 * @DATE 2025-03-11 14:03:29
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Payment extends BaseEntity<Payment> {
    /** 收款银行账号 (无默认值) */
    private String bankAccount;

    /** 收款银行名称 (无默认值) */
    private String bankName;

    /** 付款人 (无默认值) */
    private String payer;

    /** 备注 (无默认值) */
    private String memo;

    /** 操作员 (无默认值) */
    private String operator;

    /** 支付配置ID (无默认值) */
    private String paymentConfigId;

    /** 支付配置名称 (无默认值) */
    private String paymentConfigName;

    /** 支付手续费 (无默认值) */
    private BigDecimal paymentFee;

    /** 支付类型 (无默认值) */
    private Integer paymentType;

    /** 支付编号 (无默认值) */
    private String paymentSn;

    /** 支付状态 (无默认值) */
    private Integer paymentStatus;

    /** 支付金额 (无默认值) */
    private BigDecimal totalAmount;

    /** 预存款ID (无默认值) */
    private String depositId;

    /** 订单ID (无默认值) */
    private String orderId;
}