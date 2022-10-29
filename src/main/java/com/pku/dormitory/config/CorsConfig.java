package com.pku.dormitory.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 允许跨域的注解类
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 允许跨域访问的路径
                .allowedOrigins("*") // 允许跨域访问的源
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 允许跨域访问的方法
                .maxAge(168000) // 预检间隔时间
                .allowedHeaders("*") // 允许头部设置
                .allowCredentials(true); // 是否发送cookie
    }
}
