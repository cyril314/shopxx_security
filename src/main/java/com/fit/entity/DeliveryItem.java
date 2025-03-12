package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 物流项
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class DeliveryItem extends BaseEntity<DeliveryItem> {
    /** 物流数量 (无默认值) */
    private Integer deliveryQuantity;

    /** 商品HTML静态文件路径 (无默认值) */
    private String productHtmlFilePath;

    /** 商品名称 (无默认值) */
    private String productName;

    /** 商品货号 (无默认值) */
    private String productSn;

    /** 商品ID (无默认值) */
    private String productId;

    /** 退货 (无默认值) */
    private String reshipId;

    /** 发货 (无默认值) */
    private String shipId;
}