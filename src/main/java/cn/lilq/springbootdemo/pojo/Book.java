package cn.lilq.springbootdemo.pojo;

import javax.persistence.*;
import java.util.Objects;

/**
 * @auther: Li Liangquan
 * @date: 2020/9/27 10:43
 */

@Entity
@Table(name = "TAB_Book")
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String mess;

    public Book(Long id, String name, String mess) {
        this.id = id;
        this.name = name;
        this.mess = mess;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(mess, book.mess);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mess);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mess='" + mess + '\'' +
                '}';
    }
}
