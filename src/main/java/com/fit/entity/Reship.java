package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 退货
 * @Author AIM
 * @DATE 2025-03-11 13:51:32
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Reship extends BaseEntity<Reship> {
    /** 物流编号 (无默认值) */
    private String deliverySn;

    /** 物流费用 (无默认值) */
    private BigDecimal deliveryFee;

    /** 配送方式名称 (无默认值) */
    private String deliveryTypeName;

    /** 物流公司名称 (无默认值) */
    private String deliveryCorpName;

    /** 配送方式id (无默认值) */
    private String deliveryTypeId;

    /** 备注 (无默认值) */
    private String memo;

    /** 退货编号 (无默认值) */
    private String reshipSn;

    /** 退货地址 (无默认值) */
    private String shipAddress;

    /** 退货地区 (无默认值) */
    private String shipArea;

    /** 退货地区路径 (无默认值) */
    private String shipAreaPath;

    /** 退货手机 (无默认值) */
    private String shipMobile;

    /** 退货电话 (无默认值) */
    private String shipPhone;

    /** 退货人姓名 (无默认值) */
    private String shipName;

    /** 退货邮编 (无默认值) */
    private String shipZipCode;

    /** 订单ID (无默认值) */
    private String orderId;
}