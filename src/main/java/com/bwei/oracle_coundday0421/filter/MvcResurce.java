package com.bwei.oracle_coundday0421.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @User feifei
 * @ClassName MvcResurce  类名
 * @Author Administrator  作者
 * @Date 2020-04-22 20:18  时间
 * @Version 1.0 版本
 */
@Configuration
public class MvcResurce implements WebMvcConfigurer {
    /*磁盘上的路径*/
    @Value("${file.upload.path}")
    private String filePath ;
    /*相对路径*/
    @Value("${file.upload.relative}")
    private String relativePath;



    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler(relativePath).addResourceLocations("file:/"+filePath);
    }
}
