package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 收货地址
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Receiver extends BaseEntity<Receiver> {
    /** 地址 (无默认值) */
    private String address;

    /** 收货地区路径 (无默认值) */
    private String areapath;

    /** 是否默认 (无默认值) */
    private Boolean isdefault;

    /** 邮编 (无默认值) */
    private String zipcode;

    /** 手机 (无默认值) */
    private String mobile;

    /** 电话 (无默认值) */
    private String phone;

    /** 收货人姓名 (无默认值) */
    private String name;

    /** 会员 (无默认值) */
    private String memberId;
}