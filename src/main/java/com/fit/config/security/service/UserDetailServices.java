package com.fit.config.security.service;

import com.fit.config.security.entity.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @AUTO 从数据库中读入用户的密码，角色信息，是否锁定，账号是否过期
 * @Author AIM
 * @DATE 2018/7/17
 */
@Component("userDetailServices")
public class UserDetailServices implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailServices.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String user_sql = "SELECT u.`id`,u.`username`,u.`password` FROM `sys_admin` u WHERE u.`USERNAME`=?";

    private final String sql = "SELECT r.`NAME` FROM `sys_role` r,`sys_admin` u WHERE u.`ROLE_ID`=r.`ID` AND u.`USERNAME`=?";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean isDebug = logger.isDebugEnabled();
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名不能为空！");
        }
        UserDetail user = jdbcTemplate.query(user_sql, new Object[]{username}, new ResultSetExtractor<UserDetail>() {
            @Override
            public UserDetail extractData(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    return new UserDetail(rs.getString("username"), rs.getString("password"), new ArrayList<>(), rs.getLong("id"));
                }
                return null;
            }
        });
        if (user == null) {
            throw new UsernameNotFoundException("不存在用户名！");
        }
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, username);
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Map<String, Object> map : mapList) {
            if (map != null && map.get("name") != null) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(map.get("name").toString());
                // 1：此处将权限信息添加到 GrantedAuthority; 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                grantedAuthorities.add(grantedAuthority);
            }
        }
        if (isDebug) {
            logger.debug("得到其权限：" + grantedAuthorities);
            logger.debug("===================================> 登陆访问第二步");
        }
        return new UserDetail(user.getUsername(), user.getPassword(), grantedAuthorities, user.getId());
    }
}