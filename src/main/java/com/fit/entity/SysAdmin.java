package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @AUTO 管理员
 * @Author AIM
 * @DATE 2025-03-05 13:09:06
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class SysAdmin extends BaseEntity<SysAdmin> {
    /** 部门 (无默认值) */
    private String department;

    /** 账号是否启用 (无默认值) */
    private Boolean isEnabled;

    /** 账号是否过期 (无默认值) */
    private Boolean isExpired;

    /** 账号是否锁定 (无默认值) */
    private Boolean isLocked;

    /** 凭证是否过期 (无默认值) */
    private Boolean isCredentialsExpired;

    /** 连续登录失败的次数 (无默认值) */
    private Integer loginFailureCount;

    /** 账号锁定日期 (无默认值) */
    private Date lockedTime;

    /** 最后登录日期 (无默认值) */
    private Date loginTime;

    /** 最后登录IP (无默认值) */
    private String loginIp;

    /** 姓名 (无默认值) */
    private String name;

    /** 邮件 (无默认值) */
    private String email;

    /** 登录密码 (无默认值) */
    private String password;

    /** 登录名 (无默认值) */
    private String username;

    /**  (无默认值) */
    private String roleId;
}