package cn.lilq.springbootdemo.dao;

import java.util.concurrent.TimeUnit;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 16:43
 */
public interface RedisDAO {
    /**
     * redis set
     * @param key 键
     * @param value 值
     * @return
     */
    String set(String key, String value);

    /**
     * redis 根据key获取value
     * @param key key
     * @return value
     */
    String get(String key);

    /**
     * 设置值，以及过期时间
     * @param key key
     * @param value value
     * @param time 设置过期时间
     * @param timeUnit 单位
     */
    void setTimeOut(String key,String value,long time, TimeUnit timeUnit);

    /**
     * 删除键值对
     * @param key key
     * @return bool
     */
    Boolean delete(String key);

    /**
     * 数组左进
     * @param key key 数组名
     * @param value 值
     */
    void leftPush(String key,String value);

    /**
     * 数组右进
     * @param key key 数组名
     * @param value 值
     */
    void rightPush(String key,String value);

    /**
     * 数组左出
     * @param key key 数组名
     * @return value
     */
    String leftPop(String key);

    /**
     * 数组右出
     * @param key key 数组名
     * @return value
     */
    String rightPop(String key);
}
