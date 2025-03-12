package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 退款
 * @Author AIM
 * @DATE 2025-03-11 14:15:14
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Refund extends BaseEntity<Refund> {
    /** 收款人 (无默认值) */
    private String payee;

    /** 退款银行名称 (无默认值) */
    private String bankName;

    /** 退款银行账号 (无默认值) */
    private String bankAccount;

    /** 操作员 (无默认值) */
    private String operator;

    /** 备注 (无默认值) */
    private String memo;

    /** 支付配置 (无默认值) */
    private String paymentConfigId;

    /** 支付配置名称 (无默认值) */
    private String paymentConfigName;

    /** 退款编号 (无默认值) */
    private String refundSn;

    /** 退款类型 (无默认值) */
    private Integer refundType;

    /** 退款金额 (无默认值) */
    private BigDecimal totalAmount;

    /** 订单 (无默认值) */
    private String orderId;

    /** 预存款 (无默认值) */
    private String depositId;
}