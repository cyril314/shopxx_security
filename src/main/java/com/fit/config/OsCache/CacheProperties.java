package com.fit.config.OsCache;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/4
 */
@Data
@Component
@ConfigurationProperties(prefix = "cache")
public class CacheProperties extends Properties {
    private boolean enabled;
    private int size;
    private int timeout;
}
