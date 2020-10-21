package com.scistor.develop.tools;


import java.util.Set;

import static com.scistor.develop.tools.BlockRedis.*;


public class RedisClient {

    /*public static JedisPool jedisPool;//非切片连接池

    public RedisClient() {
        initialPool();
    }


    public static void initialPool() {
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(10);
        config.setMaxIdle(300);
        config.setMaxTotal(6000);
        config.setMaxWaitMillis(30000);

        jedisPool = new JedisPool(config, REDIS_HOST, Integer.valueOf(REDIS_PORT));

    }


    public static synchronized Jedis getJedis() {
        if (jedisPool == null) initialPool();
        Jedis jedis = jedisPool.getResource();

        jedis.auth(REDIS_PASSWORD);
        jedis.select(Integer.valueOf(REDIS_DATABASE));

        return jedis;
    }


    public static void zadd(final String key, final double score, final String member) {
        Jedis jedis = getJedis();
        try {
            jedis.zadd(key, score, member);
            jedis.close();
            return;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return;
        }
    }

    public static void zincrby(final String key, final double score, final String member) {
        Jedis jedis = getJedis();
        try {
            jedis.zincrby(key, score, member);
            jedis.close();
            return;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return;
        }
    }

    public static void incrBy(final String key, final long score) {

        Jedis jedis = getJedis();
        try {
            jedis.incrBy(key, score);
            jedis.close();
            return;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return;
        }
    }

    public static String getRedisVal(final String key) {
        String data = null;
        Jedis jedis = getJedis();
        try {
            data = jedis.get(key);
            jedis.close();
            return data;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return null;
        }
    }

    public static void redisDel(final String key) {
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
            jedis.close();
            return;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return;
        }
    }

    public static void redisHset(String key, String field, String value) {
        Jedis jedis = getJedis();
        try {
            jedis.hset(key, field, value);
            jedis.close();
            return;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return;
        }

    }

    public static String redisHget(String key, String field) {
        Jedis jedis = getJedis();
        try {
            String str = jedis.hget(key, field);
            jedis.close();
            return str;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return null;
        }

    }


    public static void redisHdel(String key, String field) {
        Jedis jedis = getJedis();
        try {
            jedis.hdel(key, field);
            jedis.close();
            return;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return;
        }

    }


    public static Long dels(final String key) {
        Jedis jedis = getJedis();
        Long data = 0L;
        try {
            Set<String> set = jedis.keys(key);
            for (String s : set) {
                System.out.println(data);
                data++;
                redisDel(s);
            }
            jedis.close();
            return data;
        } catch (Exception e) {
            if (jedis != null) jedis.close();
            return null;
        }
    }

    public static void main(String[] args) {
        redisHset("b", "b1", "bv");
        System.out.println(redisHget("b", "b1"));
        redisHdel("b", "b1");
        System.out.println(redisHget("b", "b1"));

    }

    public static void loginSession(String openId, String sessionId) {


        //得到上一个使用此openId的用户redisSessionId
        String redisSessionId = redisHget(MAP_REDIS_KEY_SESSSION_ID, openId);
        //System.out.println("当前登录用户session>" + sessionId);
        //System.out.println("用户登录>" + openId);
        //System.out.println("此前使用该用户的session>" + redisSessionId);
        //如果有
        if (redisSessionId != null) {
            //判断这个redisSessionId如今登录的用户是否还是此oppenId
            if (openId.equals(redisHget(MAP_REDIS_KEY_OPENSESSION_ID, redisSessionId))) {
                System.out.println("该用户如今使用的还是此openid，进行销毁，取消其登陆状态");
                //进行销毁
                redisHdel(MAP_REDIS_KEY_SESSSION_ID, openId);
                redisHdel(MAP_REDIS_KEY_OPENSESSION_ID, redisSessionId);
                //进行销毁
                for (String delKey : REDIS_DEL_KEY_BEFOR_LIST) {
                    System.out.println("消掉session状态:>" + delKey + redisSessionId);
                    redisDel(delKey + redisSessionId);
                }
            }
        }
        //将自己的openId与sessionId绑定
        redisHset(MAP_REDIS_KEY_SESSSION_ID, openId, sessionId);
        //当前sessionId与openId绑定
        redisHset(MAP_REDIS_KEY_OPENSESSION_ID, sessionId, openId);
    }*/

}
