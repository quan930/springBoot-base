package cn.lilq.springbootdemo.service;

import org.springframework.amqp.core.Message;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/6 08:55
 */
public interface MQService {
    /**
     * 消息回调 订阅者模式
     * @param message
     */
    void receive(Message message);
}
