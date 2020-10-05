package cn.lilq.springbootdemo.config;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * @auther: Li Liangquan
 * @date: 2020/10/4 20:08
 * 数据库测试
 */
@Configuration
@ConditionalOnClass(Gson.class)
@ConditionalOnMissingClass("com.fasterxml.jackson.core.JsonGenerator")
@ConditionalOnBean(Gson.class)
public class GsonHttpMessageConverterConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
        GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
        converter.setGson(gson);
        return converter;
    }
}