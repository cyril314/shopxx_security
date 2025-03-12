package com.fit.config.OsCache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

import java.util.Collection;
import java.util.Collections;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/4
 */
public class OSCacheCacheManager implements CacheManager {
    private final OSCacheCache cacheAdapter;

    public OSCacheCacheManager(GeneralCacheAdministrator cacheAdministrator) {
        this.cacheAdapter = new OSCacheCache("osCache", cacheAdministrator);
    }

    @Override
    public Cache getCache(String name) {
        // 返回自定义的 OsCacheAdapter 适配器
        return cacheAdapter;
    }

    @Override
    public Collection<String> getCacheNames() {
        return Collections.singletonList("osCache");
    }
}