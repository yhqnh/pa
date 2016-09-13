package com.ikamobile.pa.dispatchapi.session;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/25.
 */
@Component
@Slf4j
public class RedisCacheManager extends AbstractCacheManager implements CacheManager {

    @Autowired
    private RedisCacheImpl redisCache;

    @Override
    protected Cache createCache(String name) throws CacheException {
        return redisCache;
    }
}
