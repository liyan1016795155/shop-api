package com.fh.utils;

import com.fh.utils.RedisPool;
import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisUtil {
    //删除
    public static void del(String key){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    //删除
    public static Long hdel(String key,String field){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            Long res = jedis.hdel(key, field);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    //设置
    public static void set(String key,String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    //设置
    public static void setEx(String key,String value,int seconds){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.setex(key, seconds, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    public static void hset(String key,String field,String value){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.hset(key,field,value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    public static String hget(String key,String field){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            String res = jedis.hget(key, field);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    public static boolean exist(String key,String field){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            Boolean res = jedis.hexists(key, field);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    public static boolean exist(String key){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            Boolean res = jedis.exists(key);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    public static void expire(String key,int seconds){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            jedis.expire(key,seconds);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }

    public static List<String> hget(String key){
        Jedis jedis =null;
        List<String> res = null;
        try {
            jedis = RedisPool.getResource();
            res = jedis.hvals(key);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
        return res;
    }

    //获取
    public static String get(String key){
        Jedis jedis =null;
        try {
            jedis = RedisPool.getResource();
            String res = jedis.get(key);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            if (null != jedis){
                jedis.close();
            }
        }
    }
}
