package cn.lilq.springbootdemo.controller;

import cn.lilq.springbootdemo.dao.OSSDAO;
import cn.lilq.springbootdemo.pojo.Book;
import cn.lilq.springbootdemo.pojo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 16:22
 * 阿里云OSS 对象存储 上传文件
 */

@Controller
public class OSSCon {
    @Autowired
    OSSDAO ossDao;

    @ResponseBody
    @RequestMapping(value = "/oss/upload", method= RequestMethod.POST)
    public Response upload(@RequestParam("file") MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String url = ossDao.upload(file,fileName);
        if ("".equals(url))
            return new Response("error",(List<Book>) null,null,null,null);
        return new Response("successful",(List<Book>) null,null,null,url);
    }
}