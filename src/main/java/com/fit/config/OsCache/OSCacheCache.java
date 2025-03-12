package com.fit.config.OsCache;

import lombok.SneakyThrows;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import com.opensymphony.oscache.general.GeneralCacheAdministrator;

import java.util.concurrent.Callable;

/**
 * @AUTO
 * @Author AIM
 * @DATE 2025/3/4
 */
public class OSCacheCache implements Cache {

    private final GeneralCacheAdministrator administrator;
    private final String name;

    public OSCacheCache(String name, GeneralCacheAdministrator administrator) {
        this.name = name;
        this.administrator = administrator;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Object getNativeCache() {
        return this.administrator;
    }

    @SneakyThrows
    @Override
    public ValueWrapper get(Object key) {
        Object value = administrator.getFromCache(key.toString());
        return (value != null ? new SimpleValueWrapper(value) : null);
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        Object value = get(key);
        if (value != null && type != null && type.isInstance(value)) {
            return type.cast(value);
        }
        return null;
    }

    // 支持带缓存加载逻辑的 get 方法
    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        Object value = get(key);
        if (value == null) {
            try {
                value = valueLoader.call();
                if (value != null) {
                    administrator.putInCache(key.toString(), value);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to load cache value", e);
            }
        }
        return (T) value;
    }

    @Override
    public void put(Object key, Object value) {
        administrator.putInCache(key.toString(), value);
    }

    @Override
    public void evict(Object key) {
        administrator.flushEntry(key.toString());
    }

    @Override
    public void clear() {
        administrator.flushAll();
    }
}
