package org.tasc.tasc_spring.api_common.config;


import redis.clients.jedis.Jedis;

public class RedisConfig {
    public static Jedis jedis = new Jedis("localhost",6379);
    public static String key = "token";
}
