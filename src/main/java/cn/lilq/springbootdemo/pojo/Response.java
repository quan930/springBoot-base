package cn.lilq.springbootdemo.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 08:38
 */
public class Response {
    private String errorMsg;
    private List<Book> books;
    private Fiery fiery;
    private Mail mail;
    private String url;

    public Response() {
    }

    public Response(String errorMsg, List<Book> books, Fiery fiery,Mail mail,String url) {
        this.errorMsg = errorMsg;
        this.books = books;
        this.fiery = fiery;
        this.mail = mail;
        this.url = url;
    }

    public Response(String errorMsg, Book book, Fiery fiery,Mail mail,String url) {
        this.errorMsg = errorMsg;
        this.books = new ArrayList<>();
        books.add(book);
        this.fiery = fiery;
        this.mail = mail;
        this.url = url;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Fiery getFiery() {
        return fiery;
    }

    public void setFiery(Fiery fiery) {
        this.fiery = fiery;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Response{" +
                "errorMsg='" + errorMsg + '\'' +
                ", books=" + books +
                ", fiery=" + fiery +
                ", mail=" + mail +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return Objects.equals(errorMsg, response.errorMsg) &&
                Objects.equals(books, response.books) &&
                Objects.equals(fiery, response.fiery) &&
                Objects.equals(mail, response.mail) &&
                Objects.equals(url, response.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorMsg, books, fiery, mail, url);
    }
}
