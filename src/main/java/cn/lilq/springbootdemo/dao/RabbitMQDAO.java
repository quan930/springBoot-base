package cn.lilq.springbootdemo.dao;

import cn.lilq.springbootdemo.pojo.Book;

import java.util.Map;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/6 08:44
 */
public interface RabbitMQDAO {
    /**
     * 发送点对点
     * @param map
     */
    void sendDirect(Map map);

    /**
     * 发布订阅
     * @param map
     */
    void sendTopic(Map map);

    /**
     * 花费消息 点对点
     * @return 消息
     */
    Book receive();
}
