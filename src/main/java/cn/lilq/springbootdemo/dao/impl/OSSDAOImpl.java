package cn.lilq.springbootdemo.dao.impl;

import cn.lilq.springbootdemo.config.AliyunOSSConfig;
import cn.lilq.springbootdemo.controller.BookCon;
import cn.lilq.springbootdemo.dao.OSSDAO;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 16:17
 */

@Repository(value = "ossDao")
public class OSSDAOImpl implements OSSDAO {
    private static Logger logger = LoggerFactory.getLogger(OSSDAOImpl.class);
    @Autowired
    OSS ossClient;
    @Autowired
    AliyunOSSConfig aliyunOSSConfig;

    @Override
    public String upload(MultipartFile file, String fileName) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(new Date());
            String fileUrl = aliyunOSSConfig.getALIYUN_OSS_FILEHOST() + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + fileName);
            // 上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(aliyunOSSConfig.getALIYUN_OSS_BUCKETNAME(), fileUrl, file.getInputStream()));
            // 设置权限(公开读)
            ossClient.setBucketAcl(aliyunOSSConfig.getALIYUN_OSS_BUCKETNAME(), CannedAccessControlList.PublicRead);
            if (result != null) {
                logger.info("------OSS文件上传成功------" + fileUrl);
                return "https://" + aliyunOSSConfig.getALIYUN_OSS_BUCKETNAME() + "." + aliyunOSSConfig.getALIYUN_OSS_ENDPOINT() + "/" + fileUrl;
            }
        }catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return "";
    }
}
