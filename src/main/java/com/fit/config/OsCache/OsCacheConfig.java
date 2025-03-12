package com.fit.config.OsCache;

import com.opensymphony.oscache.general.GeneralCacheAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/2/24
 */
@Configuration
@EnableCaching
public class OsCacheConfig {

    @Autowired
    private CacheProperties cacheProperties;

    @Bean
    public GeneralCacheAdministrator cacheManager() {
        return new GeneralCacheAdministrator(cacheProperties);
    }

    @Bean("osCacheManager")
    public CacheManager osCacheManager(GeneralCacheAdministrator administrator) {
        return new OSCacheCacheManager(administrator);
    }
}