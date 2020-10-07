package cn.lilq.springbootdemo.pojo;

import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/6 22:52
 */
public class Mail {
    private String recipient;//邮件接收人
    private String subject;  //邮件主题
    private String content;  //邮件内容

    public Mail() {
    }

    public Mail(String recipient, String subject, String content) {
        this.recipient = recipient;
        this.subject = subject;
        this.content = content;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "recipient='" + recipient + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mail mail = (Mail) o;
        return Objects.equals(recipient, mail.recipient) &&
                Objects.equals(subject, mail.subject) &&
                Objects.equals(content, mail.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipient, subject, content);
    }
}
