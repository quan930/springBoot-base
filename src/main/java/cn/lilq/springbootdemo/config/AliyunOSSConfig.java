package cn.lilq.springbootdemo.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/7 15:24
 * 阿里云oss 配置类
 */

@Configuration
public class AliyunOSSConfig {
    @Value("${aliyun.oss.endpoint}")
    private String ALIYUN_OSS_ENDPOINT;
    @Value("${aliyun.oss.accessKeyId}")
    private String ALIYUN_OSS_ACCESSKEYID;
    @Value("${aliyun.oss.accessKeySecret}")
    private String ALIYUN_OSS_ACCESSKEYSECRET;
    @Value("${aliyun.oss.bucketName}")
    private String ALIYUN_OSS_BUCKETNAME;
    @Value("${aliyun.oss.filehost}")
    private String ALIYUN_OSS_FILEHOST;

    @Bean("ossClient")
    public OSS ossClient(){
        return new OSSClientBuilder().build(ALIYUN_OSS_ENDPOINT, ALIYUN_OSS_ACCESSKEYID, ALIYUN_OSS_ACCESSKEYSECRET);
    }

    public String getALIYUN_OSS_ENDPOINT() {
        return ALIYUN_OSS_ENDPOINT;
    }

    public String getALIYUN_OSS_BUCKETNAME() {
        return ALIYUN_OSS_BUCKETNAME;
    }

    public String getALIYUN_OSS_FILEHOST() {
        return ALIYUN_OSS_FILEHOST;
    }
}
