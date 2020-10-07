package cn.lilq.springbootdemo.dao;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 16:15
 */
public interface OSSDAO {

    /**
     * 上传文件
     * @param file 文件
     * @param fileName
     * @return
     */
    /**
     * 上传文件
     * @param file MultipartFile对象
     * @param fileName 文件名
     * @return 成功返回url 否则返回""
     */
    String upload(MultipartFile file, String fileName);
}
