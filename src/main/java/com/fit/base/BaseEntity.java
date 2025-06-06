package com.fit.base;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @AUTO Bean基类
 * @FILE BaseEntity.java
 * @DATE 2018-3-23 下午2:40:09
 * @Author AIM
 */
@Data
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体编号（唯一标识）
     */
    protected String id;

    /**
     * 是否被禁用 0禁用1正常  (默认值为: 0)
     */
    protected Boolean enabled = true;
    /**
     * 创建人 (无默认值)
     */
    protected Long createUser;

    /**
     * 创建时间 (无默认值)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date createTime;

    /**
     * 修改人 (无默认值)
     */
    protected Long updateUser;

    /**
     * 修改时间 (无默认值)
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    protected Date modifyTime;

    protected Long offset;
}
