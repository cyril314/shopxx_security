package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 预存款
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Deposit extends BaseEntity<Deposit> {
    /** 当前余额 (无默认值) */
    private BigDecimal balance;

    /** 存入金额 (无默认值) */
    private BigDecimal credit;

    /** 支出金额 (无默认值) */
    private BigDecimal debit;

    /** 预存款操作类型 (无默认值) */
    private Integer depositType;

    /** 会员ID (无默认值) */
    private String memberId;
}