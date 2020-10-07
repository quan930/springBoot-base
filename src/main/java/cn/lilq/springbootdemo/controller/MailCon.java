package cn.lilq.springbootdemo.controller;

import cn.lilq.springbootdemo.dao.JMailDAO;
import cn.lilq.springbootdemo.pojo.Book;
import cn.lilq.springbootdemo.pojo.Mail;
import cn.lilq.springbootdemo.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 10:07
 */
@Controller
public class MailCon {
    private static Logger logger = LoggerFactory.getLogger(MailCon.class);
    @Autowired
    JMailDAO jMailDAO;

    /**
     * 发送简单邮件
     * @param mail 邮件对象
     * @return response
     */
    @ResponseBody
    @RequestMapping(value="/mail/simple", method= RequestMethod.POST)
    public Response sendSimpleMail(@RequestBody Mail mail){
        int res = jMailDAO.sendSimpleMail(mail);
        if (res!=-1)
            return new Response("successful",(List<Book>)null,null,mail);
        logger.error("发送失败"+mail);
        return new Response("error",(List<Book>)null,null,null);
    }

    /**
     * 发送模版邮件 模版为：resources/templates/emailTemplate.html
     * @param mail  邮件对象
     * @return response
     */
    @ResponseBody
    @RequestMapping(value="/mail/template", method= RequestMethod.POST)
    public Response sendTemplateMail(@RequestBody Mail mail){
        int res = jMailDAO.sendTemplateMail(mail);
        if (res!=-1)
            return new Response("successful",(List<Book>)null,null,mail);
        logger.error("发送失败"+mail);
        return new Response("error",(List<Book>)null,null,null);
    }

}