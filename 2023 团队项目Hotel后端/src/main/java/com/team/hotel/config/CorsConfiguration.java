package com.team.hotel.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")//允许哪些域访问
                .allowedMethods("GET","POST","PUT","DELETE","HEAD","OPTIONS")//允许哪些方法访问
                .allowCredentials(true)//是否允许携带cookie
                .maxAge(3600)//设置浏览器询问的有效期
                .allowedHeaders("*");//
    }
}