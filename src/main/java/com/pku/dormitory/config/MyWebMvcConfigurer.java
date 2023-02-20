package com.pku.dormitory.config;

import com.pku.dormitory.interceptor.AuthInterceptor;
import com.pku.dormitory.interceptor.TeamInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 如果继承WebMvcConfigurationSupport类则默认配置会失效
 * 因此选择实现WebMvcConfiguration接口
 *
 * @author Yonmin
 * @date 2022/12/8 12:28
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    AuthInterceptor authInterceptor;

    /**
     * 注册自定义拦截器
     *
     * @param registry 拦截器注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login/**");

        registry.addInterceptor(teamInterceptor())
                .addPathPatterns("/team/**");
    }

    /**
     * 允许跨域的注解类
     *
     * @param registry 跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许跨域访问的路径
        registry.addMapping("/**")
                // 允许跨域访问的源
                .allowedOrigins("*")
                // 允许跨域访问的方法
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                // 预检间隔时间
                .maxAge(168000)
                // 允许头部设置
                .allowedHeaders("*")
                // 是否发送cookie
                .allowCredentials(true);
    }

    @Bean
    public TeamInterceptor teamInterceptor() {
        return new TeamInterceptor();
    }

}
