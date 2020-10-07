package cn.lilq.springbootdemo.dao.impl;

import cn.lilq.springbootdemo.controller.BookCon;
import cn.lilq.springbootdemo.dao.JMailDAO;
import cn.lilq.springbootdemo.pojo.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 09:58
 */

@Repository(value = "jMailDAO")
public class JMailDAOImpl implements JMailDAO {
    private JavaMailSender javaMailSender;
    private TemplateEngine templateEngine;
    private static String MAIL_SENDER = "llqzuishuai@163.com";

    @Autowired
    public JMailDAOImpl(TemplateEngine templateEngine,JavaMailSender javaMailSender) {
        this.templateEngine = templateEngine;
        this.javaMailSender = javaMailSender;
    }

    //logger
    private static Logger logger = LoggerFactory.getLogger(JMailDAOImpl.class);

    @Override
    public int sendSimpleMail(Mail mail) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            //邮件发送人
            simpleMailMessage.setFrom(MAIL_SENDER);
            //邮件接收人
            simpleMailMessage.setTo(mail.getRecipient());
            //邮件主题
            simpleMailMessage.setSubject(mail.getSubject());
            //邮件内容
            simpleMailMessage.setText(mail.getContent());
            javaMailSender.send(simpleMailMessage);
            return 1;
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
            return -1;
        }
    }

    @Override
    public int sendTemplateMail(Mail mail) {
        MimeMessage mimeMailMessage = null;
        try {
            mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setFrom(MAIL_SENDER);
            mimeMessageHelper.setTo(mail.getRecipient());
            mimeMessageHelper.setSubject(mail.getSubject());

            //创建邮件正文
            Context context = new Context();
            context.setVariable("content", mail.getContent());
            String emailContent = templateEngine.process("emailTemplate", context);

            mimeMessageHelper.setText(emailContent, true);
            javaMailSender.send(mimeMailMessage);
            return 1;
        } catch (Exception e) {
            logger.error("邮件发送失败", e.getMessage());
            return -1;
        }
    }
}
