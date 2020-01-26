package com.zhd.ultimate.sociology.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2020-01-26 16:57
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/templates/");
        //映射图片保存地址
        registry.addResourceHandler("/image/**").addResourceLocations("file:" + uploadDir);
        super.addResourceHandlers(registry);
    }
}
