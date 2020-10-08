package cn.lilq.springbootdemo.controller;

import cn.lilq.springbootdemo.dao.RabbitMQDAO;
import cn.lilq.springbootdemo.pojo.Book;
import cn.lilq.springbootdemo.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/6 09:01
 * mq controller  RabbitMQ测试类
 * 拦截器  ?token=123
 */

@Controller
public class MQCon {
    private static Logger logger = LoggerFactory.getLogger(MQCon.class);

    @Autowired
    RabbitMQDAO rabbitMQDAO;

    /**
     * 消费队列
     * @return 一条队列
     */
    @ResponseBody
    @RequestMapping(value="/mq/direct", method= RequestMethod.GET)
    public Response getMQ() {
        Book book = rabbitMQDAO.receive();
        logger.debug("获取队列"+book);
        if (book==null)
            return new Response("error",(List<Book>) null,null,null,null);
        return new Response("successful",book,null,null,null);
    }

    /**
     * 生产队列 点对点
     * @param book book
     * @return response
     */
    @ResponseBody
    @RequestMapping(value="/mq/direct", method= RequestMethod.POST)
    public Response directMQ(@RequestBody Book book) {
        Map<String,Book> map = new HashMap<>();
        map.put(String.valueOf(book.getId()),book);
        rabbitMQDAO.sendDirect(map);
        logger.debug("生产队列"+book);
        return new Response("successful",(List<Book>) null,null,null,null);
    }

    /**
     * 发布订阅
     * @param book book
     * @return response
     */
    @ResponseBody
    @RequestMapping(value="/mq/topic", method= RequestMethod.POST)
    public Response addMQ(@RequestBody Book book) {
        Map<String,Book> map = new HashMap<>();
        map.put(String.valueOf(book.getId()),book);
        rabbitMQDAO.sendTopic(map);
        logger.debug("发布订阅"+book);
        return new Response("successful",(List<Book>) null,null,null,null);
    }
}
