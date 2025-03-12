package com.fit.web.admin;

import com.opensymphony.oscache.base.Cache;
import com.opensymphony.oscache.web.ServletCacheAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;
import java.util.ArrayList;
import java.util.Date;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/12
 */
@Controller
@RequestMapping("/admin/cache")
public class CacheController {

    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @RequestMapping("/flush")
    public String flush(HttpServletRequest request) {
        // 刷新 Spring 管理的缓存
        cacheManager.getCacheNames().forEach(cacheName -> cacheManager.getCache(cacheName).clear());
        // 刷新自定义缓存
        Cache cache = ServletCacheAdministrator.getInstance(request.getSession().getServletContext()).getCache(request, PageContext.APPLICATION_SCOPE);
        cache.flushAll(new Date());
        // 刷新 FreeMarker 模板缓存
        freeMarkerConfigurer.getConfiguration().clearTemplateCache();
        request.setAttribute("actionMessages", new ArrayList<String>());
        request.setAttribute("errorMessages", new ArrayList<String>());
        request.setAttribute("fieldErrors", new ArrayList<String>());
        return "/admin/success";
    }
}