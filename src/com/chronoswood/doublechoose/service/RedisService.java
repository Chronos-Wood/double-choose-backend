package com.chronoswood.doublechoose.service;

import com.alibaba.fastjson.JSON;
import com.chronoswood.doublechoose.cache.KeyPrefix;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
@Slf4j
public class RedisService {

    private JedisPool jedisPool;

    @Autowired
    public RedisService(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    /**
     * 根据key获取值
     * @param prefix 前缀对象 设置keyprefix以及过期时间
     * @param key 追加的key
     * @param clazz value的类型
     * @param <T> value的类型
     * @return T类型的值对象
     */
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        try (Jedis jedis = jedisPool.getResource()) {
            if(prefix != null) {
                String realKey = prefix.getPrefix() + key;
                String value = jedis.get(realKey);
                return string2Bean(value, clazz);
            }
            return null;
        }
    }

    /**
     * set 值
     * @param prefix 前缀对象 设置keyprefix以及过期时间
     * @param key 追加的key
     * @param value set的值
     * @param <T> value的类型
     * @return 成功或失败
     */
    public <T> boolean set(KeyPrefix prefix, String key, T value) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (key != null && value != null && prefix != null) {
                String json = bean2String(value);
                if (StringUtils.hasText(json)) {
                    //实际存储的key， 放key冲突意外覆盖的手段
                    String realKey = prefix.getPrefix() + key;
                    if (prefix.expireSeconds() == -1) {
                        jedis.set(realKey, json);
                    } else {
                        jedis.setex(realKey, prefix.expireSeconds(), json);
                    }
                    return Boolean.TRUE;
                }
            }
            return Boolean.FALSE;
        }
    }

    /**
     * 判断key是否存在
     * @param prefix 前缀对象 用来获取realKey
     * @param key 追加的key
     * @return 是否存在
     */
    public boolean exist(KeyPrefix prefix, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (prefix != null && key != null) {
                String realKey = prefix.getPrefix() + key;
                return jedis.exists(realKey);
            }
            return  false;
        }
    }

    /**
     * 给realkey对应的值增加1
     * @param prefix 前缀对象 用来获取realKey
     * @param key 追加的key
     * @return 增加后的值
     */
    public Long incr(KeyPrefix prefix, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (prefix != null && key != null) {
                String realKey = prefix.getPrefix() + key;
                return jedis.incr(realKey);
            }
            return  -1L;
        }
    }

    /**
     * 给realkey对应的值减1
     * @param prefix 前缀对象 用来获取realKey
     * @param key 追加key
     * @return 减少1后的值
     */
    public Long decr(KeyPrefix prefix, String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            if (prefix != null && key != null) {
                String realKey = prefix.getPrefix() + key;
                return jedis.decr(realKey);
            }
            return  -1L;
        }
    }

    @SuppressWarnings("unchecked")
    private <T> T string2Bean(String value, Class<T> clazz) {
        if (!StringUtils.hasText(value)) {
            return null;
        }
        try {
            if (clazz == int.class || clazz == Integer.class) {
                return (T) Integer.valueOf(value);
            }
            if (clazz == long.class || clazz == Long.class) {
                return (T) Long.valueOf(value);
            }
            if (clazz == char.class || clazz == Character.class) {
                return (T) (Character) value.charAt(0);
            }
            if (clazz == String.class) {
                return (T) value;
            }
            return JSON.toJavaObject(JSON.parseObject(value), clazz);
        } catch (Exception e) {
            log.error("",e);
            return null;
        }
    }

    private <T> String bean2String(T bean) {
        Class clazz;
        if (bean == null) return null;
        if ((clazz = bean.getClass()) == int.class || clazz == Integer.class
                || clazz == long.class || clazz == Long.class || clazz == char.class
                || clazz == Character.class) {
            return bean + "";
        }
        if (clazz == String.class) {
            return (String) bean;
        }
        if (clazz == Boolean.class || clazz == boolean.class) {
            boolean bol = (Boolean) bean;
            return bol ? "true" : "false";
        }
        try {
            return JSON.toJSONString(bean);
        } catch (Exception e) {
            return null;
        }
    }
}
