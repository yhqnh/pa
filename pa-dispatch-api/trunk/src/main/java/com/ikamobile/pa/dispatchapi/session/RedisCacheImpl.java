package com.ikamobile.pa.dispatchapi.session;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;

/**
 * Created by yanghuqiang(yhqnh@aliyun.com) on 2016/7/25.
 */
@Component
@Slf4j
public class RedisCacheImpl implements Cache {

    @Value(value = "${globalSessionTimeout}")
    private long globalSessionTimeout = 1800000;

    private static final String SESSION_HASH = "SHIRO_SESSION";

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public Object get(Object key) throws CacheException {
        return redisTemplate.opsForHash().get(SESSION_HASH, key);
    }

    @Override
    public Object put(Object key, Object value) throws CacheException {
        redisTemplate.opsForHash().put(SESSION_HASH, key, value);
        return value;
    }

    @Override
    public Object remove(Object key) throws CacheException {
        redisTemplate.opsForHash().delete(SESSION_HASH, key);
        return key;
    }

    @Override
    public void clear() throws CacheException {
        redisTemplate.opsForHash().delete(SESSION_HASH);
    }

    @Override
    public int size() {
        return redisTemplate.opsForHash().size(SESSION_HASH).intValue();
    }

    @Override
    public Set keys() {
        return redisTemplate.opsForHash().keys(SESSION_HASH);
    }

    @Override
    public Collection values() {
        return redisTemplate.opsForHash().values(SESSION_HASH);
    }
}
