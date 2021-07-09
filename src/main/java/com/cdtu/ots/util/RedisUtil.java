package com.cdtu.ots.util;

import redis.clients.jedis.Jedis;

public class RedisUtil {
    public Jedis getRedis(){
        String host = "localhost";
        int port = 6379;
        Jedis jedis = null;
        try {
            jedis = new Jedis(host, port);
            jedis.auth("root3306");

            jedis.select(1);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return  jedis;
    }
}
