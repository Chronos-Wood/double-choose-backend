package com.chronoswood.doublechoose.configuration;


import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisConfig {

    private RedisProperties redisProperties;

    public JedisConfig(RedisProperties redisProperties) {
        this.redisProperties = redisProperties;
    }

    @Bean
    public JedisPool jedisPool() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperties.getJedis().getPool().getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisProperties.getJedis().getPool().getMaxActive());
        jedisPoolConfig.setMinIdle(redisProperties.getJedis().getPool().getMinIdle());
        return new JedisPool(jedisPoolConfig, redisProperties.getHost(), redisProperties.getPort(),
                (int)(redisProperties.getTimeout().toMillis()), redisProperties.getPassword(), redisProperties.getDatabase());
    }
}
