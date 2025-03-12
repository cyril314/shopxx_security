package com.fit.config.security.entity;

import com.fit.entity.SysAdmin;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @AUTO 安全用户验证实体
 * @FILE UserDetail.java
 * @DATE 2017-11-2 下午4:32:31
 * @Author AIM
 */
public class UserDetail extends User {

    private Long id;

    public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities, Long id) {
        super(username, password, authorities);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}