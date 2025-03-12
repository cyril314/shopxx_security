package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 订单项
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class OrderItem extends BaseEntity<OrderItem> {
    /** 发货数量 (无默认值) */
    private Integer deliveryquantity;

    /** 商品HTML静态文件路径 (无默认值) */
    private String producthtmlfilepath;

    /** 商品数量 (无默认值) */
    private Integer productquantity;

    /** 商品价格 (无默认值) */
    private BigDecimal productprice;

    /** 商品名称 (无默认值) */
    private String productname;

    /** 商品货号 (无默认值) */
    private String productsn;

    /** 总发货量 (无默认值) */
    private Integer totaldeliveryquantity;

    /** 订单ID (无默认值) */
    private String orderId;

    /** 商品货号ID (无默认值) */
    private String productId;
}