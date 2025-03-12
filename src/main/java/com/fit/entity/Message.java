package com.fit.entity;

import com.fit.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @AUTO 站内消息
 * @Author AIM
 * @DATE 2025-03-11 15:18:11
 */
@Data
@Builder
@NoArgsConstructor //无参数的构造方法
@AllArgsConstructor //包含所有变量构造方法
public class Message extends BaseEntity<Message> {
    /** 消息标题 (无默认值) */
    private String title;

    /** 消息内容 (无默认值) */
    private String content;

    /** 删除状态 (无默认值) */
    private Integer deleteStatus;

    /** 是否标记已读 (无默认值) */
    private Boolean isRead;

    /** 是否在草稿箱保存 (无默认值) */
    private Boolean isDraftboxSave;

    /** 消息发出会员,为null时表示管理员 (无默认值) */
    private String fromMemberId;

    /** 消息接收会员,为null时表示管理员 (无默认值) */
    private String toMemberId;
}