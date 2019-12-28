package com.zhd.ultimate.sociology.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: zhanghaodong
 * @description
 * @date: 2019-12-27 17:45
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/home/login", "/home/showLogin")
                .excludePathPatterns("/bower_components/**")
                .excludePathPatterns("*.js")
                .excludePathPatterns("*.css")
                .excludePathPatterns("*.jpg")
                .excludePathPatterns("*.json")
                .excludePathPatterns("*.png");

    }
}
