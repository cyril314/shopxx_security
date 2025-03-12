package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 会员等级
 * @Author AIM
 * @DATE 2025-03-05 13:09:05
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class MemberRank extends BaseEntity<MemberRank> {
    /** 是否为默认等级 (无默认值) */
    private Boolean isDefault;

    /** 等級名称 (无默认值) */
    private String name;

    /** 所需积分 (无默认值) */
    private Integer point;

    /** 优惠百分比 (无默认值) */
    private Double percentage;
}