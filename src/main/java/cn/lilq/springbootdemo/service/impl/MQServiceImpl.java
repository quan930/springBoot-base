package cn.lilq.springbootdemo.service.impl;

import cn.lilq.springbootdemo.pojo.Book;
import cn.lilq.springbootdemo.service.MQService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/6 08:56
 */

@Service("mQService")
public class MQServiceImpl implements MQService {
    private static Logger logger = LoggerFactory.getLogger(MQServiceImpl.class);

    /**
     * 订阅回调
     * @param message book
     */
    @Override
    @RabbitListener(queues = "fanout.queue")
    public void receive(Message message) {
        ObjectMapper mapper=new ObjectMapper();
        Book book =  mapper.convertValue(new Gson().fromJson(new String(message.getBody()), Map.class).values().toArray()[0], Book.class);
        logger.debug(""+book);
    }
}
