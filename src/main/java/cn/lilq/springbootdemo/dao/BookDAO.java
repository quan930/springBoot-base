package cn.lilq.springbootdemo.dao;

import cn.lilq.springbootdemo.pojo.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 09:42
 * Book表
 */
@Repository
public interface BookDAO extends JpaRepository<Book,Long> {
}
