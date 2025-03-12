package com.fit.config;

import freemarker.core.Environment;
import freemarker.template.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/7
 */
@Component("authenticationDirective")
public class FreemarkerAuthenticationDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map map, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        if (map.isEmpty()) {
            throw new TemplateException("本模板宏必须提供表达式参数", env);
        }
        // 获取 property 参数
        String property = map.get("property").toString();
        // 获取当前认证信息
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String result = getPropertyValue(authentication, property);
            if (result != null) {
                env.getOut().write(result);
            }
        }
    }

    /**
     * 根据 property 参数获取对应的值
     */
    private String getPropertyValue(Authentication authentication, String property) {
        switch (property) {
            case "name":
                return authentication.getName(); // 返回用户名
            case "principal":
                return authentication.getPrincipal().toString(); // 返回 Principal 对象
            case "details":
                return authentication.getDetails() != null ? authentication.getDetails().toString() : null; // 返回 Details
            case "credentials":
                return authentication.getCredentials() != null ? authentication.getCredentials().toString() : null; // 返回 Credentials
            default:
                return null;
        }
    }
}