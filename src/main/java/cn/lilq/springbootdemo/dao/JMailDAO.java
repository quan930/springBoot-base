package cn.lilq.springbootdemo.dao;

import cn.lilq.springbootdemo.pojo.Mail;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 09:58
 */
public interface JMailDAO {
    /**
     * 发送简单邮件
     * @param mail 邮件对象
     * @return -1 error 1 successful
     */
    int sendSimpleMail(Mail mail);

    /**
     * 发送模版邮件
     * @param mail 邮件对象
     * @return -1 error 1 successful
     */
    int sendTemplateMail(Mail mail);
}
