package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 购物车
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class CartItem extends BaseEntity<CartItem> {
    /** 数量 (无默认值) */
    private Integer quantity;

    /** 商品ID (无默认值) */
    private String productId;

    /** 会员ID (无默认值) */
    private String memberId;
}