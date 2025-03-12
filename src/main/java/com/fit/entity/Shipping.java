package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 发货
 * @Author AIM
 * @DATE 2025-03-11 13:51:32
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Shipping extends BaseEntity<Shipping> {
    /** 物流编号 (无默认值) */
    private String deliverySn;

    /** 物流费用 (无默认值) */
    private BigDecimal deliveryFee;

    /** 物流公司名称 (无默认值) */
    private String deliveryCorpName;

    /** 配送方式名称 (无默认值) */
    private String deliveryTypeName;

    /** 备注 (无默认值) */
    private String memo;

    /** 收货地址 (无默认值) */
    private String shipAddress;

    /** 收货地区 (无默认值) */
    private String shipArea;

    /** 收货地区路径 (无默认值) */
    private String shipAreaPath;

    /** 收货人姓名 (无默认值) */
    private String shipName;

    /** 收货电话 (无默认值) */
    private String shipPhone;

    /** 收货手机 (无默认值) */
    private String shipMobile;

    /** 收货邮编 (无默认值) */
    private String shipZipCode;

    /** 发货编号 (无默认值) */
    private String shippingSn;

    /** 配送方式 (无默认值) */
    private String deliveryTypeId;

    /** 订单 (无默认值) */
    private String orderId;
}