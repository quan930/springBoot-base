package cn.lilq.springbootdemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringbootdemoApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Test
    void testSet() {
        stringRedisTemplate.opsForValue().set("test-string-value", "你好 Redis");
    }

    @Test
    void testGet() {
        String value = stringRedisTemplate.opsForValue().get("test-string-value");
        System.out.println(value);
    }

    @Test
    void testSetTimeOut() {
        stringRedisTemplate.opsForValue().set("test-string-key-time-out", "Hello Redis", 3, TimeUnit.HOURS);
    }

    @Test
    void testDeleted() {
        stringRedisTemplate.delete("test-string-value");
    }
    @Test
    void testLeftPush() {
        redisTemplate.opsForList().leftPush("TestList", "TestLeftPush1");
    }
    @Test
    void testRightPush() {
        redisTemplate.opsForList().rightPush("TestList", "TestRightPush1");
    }
    @Test
    void testLeftPop() {
        Object leftFirstElement = redisTemplate.opsForList().leftPop("TestList");
        System.out.println(leftFirstElement);
    }
    @Test
    void testRightPop() {
        Object rightFirstElement = redisTemplate.opsForList().rightPop("TestList");
        System.out.println(rightFirstElement);
    }
    @Test
    public void testPut() {
        redisTemplate.opsForHash().put("TestHash", "FirstElement", "Hello,Redis hash.");
        System.out.println(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
//        Assert.assertTrue(redisTemplate.opsForHash().hasKey("TestHash", "FirstElement"));
    }
}
