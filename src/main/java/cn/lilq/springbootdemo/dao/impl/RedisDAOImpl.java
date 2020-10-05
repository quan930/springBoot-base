package cn.lilq.springbootdemo.dao.impl;

import cn.lilq.springbootdemo.dao.RedisDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 19:34
 */

@Repository(value = "redisDAO")
public class RedisDAOImpl implements RedisDAO {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Override
    public String set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
        return key;
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void setTimeOut(String key, String value, long time, TimeUnit timeUnit) {
        stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
    }

    @Override
    public Boolean delete(String key) {
        return stringRedisTemplate.delete(key);
    }

    @Override
    public void leftPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public void rightPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    @Override
    public String leftPop(String key) {
        return redisTemplate.opsForList().leftPop("TestList");
    }

    @Override
    public String rightPop(String key) {
        return redisTemplate.opsForList().rightPop("TestList");
    }
}
