package cn.lilq.springbootdemo.controller;

import cn.lilq.springbootdemo.dao.BookDAO;
import cn.lilq.springbootdemo.pojo.Book;
import cn.lilq.springbootdemo.pojo.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 08:01
 */
@Controller
public class BookCon {
    @Autowired
    BookDAO bookDAO;
    //logger
    private static Logger logger = LoggerFactory.getLogger(BookCon.class);

    /**
     * 根据ID获取book
     * @param id id
     * @return book
     */
    @ResponseBody
    @RequestMapping(value="/books/{id}", method= RequestMethod.GET)
    public Response getBook(@PathVariable Long id) {
        logger.debug("查询数据"+id);
        return bookDAO.findById(id).map(value -> new Response("successful", value,null,null,null)).orElseGet(() -> new Response("error", (Book) null,null,null,null));
    }

    /**
     * add book
     * @param book
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/books",method = RequestMethod.POST)
    public Response addBook(@RequestBody Book book){
        logger.debug("增加book"+book);
        bookDAO.save(book);
        return new Response("successful",book,null,null,null);
    }

    /**
     * 删除book 根据id
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
    public Response deleteBook(@PathVariable Long id) {
        Optional<Book> book = bookDAO.findById(id);
        if (book.isPresent()){
            logger.debug("删除book"+id);
            bookDAO.deleteById(id);
            return new Response("successful",book.get(),null,null,null);
        }else {
            return new Response("error", (List<Book>) null,null,null,null);
        }
    }

    /**
     * 查询全部book
     * @return bookList
     */
    @ResponseBody
    @RequestMapping(value="/books", method=RequestMethod.GET)
    public Response bookList(){
        logger.debug("list"+bookDAO.findAll());
        return new Response("successful",bookDAO.findAll(),null,null,null);
    }
}
