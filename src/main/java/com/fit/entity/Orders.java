package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 订单
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Orders extends BaseEntity<Orders> {
    /** 订单编号 (无默认值) */
    private String orderSn;

    /** 订单状态 (无默认值) */
    private Integer orderStatus;

    /** 已付金额 (无默认值) */
    private BigDecimal paidAmount;

    /** 订单总额 (无默认值) */
    private BigDecimal totalAmount;

    /** 配送费用 (无默认值) */
    private BigDecimal deliveryFee;

    /** 配送方式名称 (无默认值) */
    private String deliveryTypeName;

    /** 附言 (无默认值) */
    private String memo;

    /** 支付费用 (无默认值) */
    private BigDecimal paymentFee;

    /** 支付状态 (无默认值) */
    private Integer paymentStatus;

    /** 支付方式名称 (无默认值) */
    private String paymentConfigName;

    /** 商品总价格 (无默认值) */
    private BigDecimal productTotalPrice;

    /** 商品总数 (无默认值) */
    private Integer productTotalQuantity;

    /** 商品重量 (无默认值) */
    private Double productWeight;

    /** 商品重量单位 (无默认值) */
    private Integer productWeightUnit;

    /** 收货地区 (无默认值) */
    private String shipArea;

    /** 收货地区路径 (无默认值) */
    private String shipAreaPath;

    /** 收货地址 (无默认值) */
    private String shipAddress;

    /** 收货人姓名 (无默认值) */
    private String shipName;

    /** 收货手机 (无默认值) */
    private String shipMobile;

    /** 收货电话 (无默认值) */
    private String shipPhone;

    /** 收货邮编 (无默认值) */
    private String shipZipCode;

    /** 发货状态 (无默认值) */
    private Integer shipStatus;

    /** 会员ID (无默认值) */
    private String memberId;

    /** 配送方式ID (无默认值) */
    private String deliverytypeId;

    /** 支付方式ID (无默认值) */
    private String paymentconfigId;
}