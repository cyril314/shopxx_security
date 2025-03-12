package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @AUTO 商品
 * @Author AIM
 * @DATE 2025-03-05 18:20:49
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Product extends BaseEntity<Product> {
    /** 描述 (无默认值) */
    private String description;

    /** 被占用库存数 (无默认值) */
    private Integer freezeStore;

    /** HTML静态文件路径 (无默认值) */
    private String htmlFilePath;

    /** 是否上架 (无默认值) */
    private Boolean isMarketable;

    /** 是否为精品商品 (无默认值) */
    private Boolean isBest;

    /** 是否为热销商品 (无默认值) */
    private Boolean isHot;

    /** 是否为新品商品 (无默认值) */
    private Boolean isNew;

    /** 品牌 (无默认值) */
    private String brandId;

    /** 市场价格 (无默认值) */
    private BigDecimal marketPrice;

    /** 页面关键词 (无默认值) */
    private String metaKeywords;

    /** 页面描述 (无默认值) */
    private String metaDescription;

    /** 商品名称 (无默认值) */
    private String name;

    /** 积分 (无默认值) */
    private Integer point;

    /** 商品价格 (无默认值) */
    private BigDecimal price;

    /** 商品库存数量 (无默认值) */
    private Integer store;

    /** 商品重量 (无默认值) */
    private Double weight;

    /** 重量单位 (无默认值) */
    private Integer weightUnit;

    /** 货号 (无默认值) */
    private String productSn;

    /** 商品图片路径存储 (无默认值) */
    private String productImages;

    /** 商品分类 (无默认值) */
    private String productCategoryId;

    /** 商品类型 (无默认值) */
    private String productTypeId;
}