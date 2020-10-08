package cn.lilq.springbootdemo.controller;

import cn.lilq.springbootdemo.dao.RedisDAO;
import cn.lilq.springbootdemo.pojo.Book;
import cn.lilq.springbootdemo.pojo.Fiery;
import cn.lilq.springbootdemo.pojo.Response;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 19:47
 * 热度类 用于描述商品的热度
 * test redis
 * 拦截器  ?token=123
 */
@Controller
public class FieryCon {
    @Autowired
    RedisDAO redisDAO;
    //logger
    private static Logger logger = LoggerFactory.getLogger(FieryCon.class);

    /**
     * 根据id 获得指定书籍热度对象
     * @param id id
     * @return 查询后的对象
     */
    @ResponseBody
    @RequestMapping(value="/fierys/{id}", method=RequestMethod.GET)
    public Response getFieryById(@PathVariable Long id){
        String value = redisDAO.get(String.valueOf(id));
        logger.debug(value);
        return new Response("successful",(List<Book>) null,new Gson().fromJson(value,Fiery.class),null,null);
    }

    /**
     * 设置书籍 热度
     * @param fiery 热度对象
     * @return 提交后的热度对象
     */
    @ResponseBody
    @RequestMapping(value="/fierys", method=RequestMethod.POST)
    public Response addFiery(@RequestBody Fiery fiery){
        String value = redisDAO.set(fiery.getId()+"",new Gson().toJson(fiery));
        logger.debug(value);
        return new Response("successful",(List<Book>) null,fiery,null,null);
    }


    /**
     * 提高书籍热度 redis
     * @param id 书籍id
     * @return 提高后的对象
     */
    @ResponseBody
    @RequestMapping(value="/fierys/{id}", method=RequestMethod.PUT)
    public Response upFiery(@PathVariable Long id){
        Fiery fiery = new Gson().fromJson(redisDAO.get(String.valueOf(id)),Fiery.class);
        fiery.setFiery(fiery.getFiery()+1);
        String value = redisDAO.set(fiery.getId()+"",new Gson().toJson(fiery));
        logger.debug(value);
        return new Response("successful",(List<Book>) null,fiery,null,null);
    }
}
