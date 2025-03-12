package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 配送方式
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class DeliveryType extends BaseEntity<DeliveryType> {
    /** 续重量 (无默认值) */
    private Double continueWeight;

    /** 续重价格 (无默认值) */
    private BigDecimal continueWeightPrice;

    /** 续重单位 (无默认值) */
    private Integer continueWeightUnit;

    /** 首重量 (无默认值) */
    private Double firstWeight;

    /** 首重价格 (无默认值) */
    private BigDecimal firstWeightPrice;

    /** 首重单位 (无默认值) */
    private Integer firstWeightUnit;

    /** 介绍 (无默认值) */
    private String description;

    /** 配送方式名称 (无默认值) */
    private String name;

    /** 排序 (无默认值) */
    private Integer sort;

    /** 配送类型 (无默认值) */
    private Integer deliveryMethod;

    /** 默认物流公司ID (无默认值) */
    private String deliveryCorpId;
}