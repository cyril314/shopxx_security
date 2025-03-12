package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @AUTO 会员信息
 * @Author AIM
 * @DATE 2025-03-11 14:25:04
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Member extends BaseEntity<Member> {
    /** 用户名 (无默认值) */
    private String username;

    /** 密码 (无默认值) */
    private String password;

    /** 邮箱 (无默认值) */
    private String email;

    /** 预存款 (无默认值) */
    private BigDecimal deposit;

    /** 账号是否启用 (无默认值) */
    private Boolean isEnabled;

    /** 账号是否锁定 (无默认值) */
    private Boolean isLocked;

    /** 账号锁定日期 (无默认值) */
    private Date lockedTime;

    /** 最后登录IP (无默认值) */
    private String loginIp;

    /** 最后登录日期 (无默认值) */
    private Date loginTime;

    /** 连续登录失败的次数 (无默认值) */
    private Integer loginFailureCount;

    /** 密码找回Key (无默认值) */
    private String passwordRecoverKey;

    /** 积分 (无默认值) */
    private Integer point;

    /** 注册IP (无默认值) */
    private String registerIp;

    /** 密码保护问题答案 (无默认值) */
    private String safeAnswer;

    /** 密码保护问题 (无默认值) */
    private String safeQuestion;

    /** 会员等级 (无默认值) */
    private String memberRankId;
}