package cn.lilq.springbootdemo.dao.impl;

import cn.lilq.springbootdemo.dao.RabbitMQDAO;
import cn.lilq.springbootdemo.pojo.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/6 08:47
 */
@Repository(value = "rabbitMQDAO")
@EnableRabbit
public class RabbitMQDAOImpl implements RabbitMQDAO {
    private final AmqpAdmin amqpAdmin;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMQDAOImpl(AmqpAdmin amqpAdmin, RabbitTemplate rabbitTemplate) {
        this.amqpAdmin = amqpAdmin;
        this.rabbitTemplate = rabbitTemplate;
        init();
    }

    @Override
    public void sendDirect(Map map) {
        rabbitTemplate.convertAndSend( "exchange.direct" , "direct.queue" , map );
    }

    @Override
    public void sendTopic(Map map) {
        rabbitTemplate.convertAndSend( "exchange.fanout" , "", map );
    }

    @Override
    public Book receive() {
        Object o = rabbitTemplate.receiveAndConvert( "direct.queue" );
        if (o==null)
            return null;
        Map<String,Book> bookMap =  (Map<String,Book>)o;
        ObjectMapper mapper=new ObjectMapper();
        return mapper.convertValue(bookMap.values().toArray()[0], Book.class);
    }

    private void init(){
        //创建Exchange
        amqpAdmin.declareExchange( new DirectExchange( "exchange.direct") );
        amqpAdmin.declareExchange( new FanoutExchange( "exchange.fanout") );
        amqpAdmin.declareExchange( new TopicExchange( "exchange.topic") );

        //创建Queue
        amqpAdmin.declareQueue( new Queue( "direct.queue" , true ) );
        amqpAdmin.declareQueue( new Queue( "fanout.queue" , true ) );

        //绑定Queue
        amqpAdmin.declareBinding( new Binding( "direct.queue" , Binding.DestinationType.QUEUE , "exchange.direct" , "direct.queue" , null ) );
        amqpAdmin.declareBinding( new Binding( "fanout.queue" , Binding.DestinationType.QUEUE , "exchange.direct" , "fanout.queue" , null ) );
        amqpAdmin.declareBinding( new Binding( "direct.queue" , Binding.DestinationType.QUEUE , "exchange.fanout" , "" , null ) );
        amqpAdmin.declareBinding( new Binding( "fanout.queue" , Binding.DestinationType.QUEUE , "exchange.fanout" , "" , null ) );
        amqpAdmin.declareBinding( new Binding( "direct.queue" , Binding.DestinationType.QUEUE , "exchange.topic" , "direct.#" , null ) );
        amqpAdmin.declareBinding( new Binding( "fanout.queue" , Binding.DestinationType.QUEUE , "exchange.topic" , "direct.*" , null ) );
    }
}
