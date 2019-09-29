package com.sse.kaizhong.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/success").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/add.html").setViewName("add");
        registry.addViewController("/edit.html").setViewName("edit");

//        registry.addViewController("/my").setViewName("index2");
    }
}
