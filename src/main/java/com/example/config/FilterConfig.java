package com.example.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.filter.JwtFilter2;
import com.example.filter.UrlFilter;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class FilterConfig {
    
    @Bean //서버가 구동될때 자동으로 호출됨
    // JwtFilter2필터가 적용되는 url을 설정
    public FilterRegistrationBean<JwtFilter2> filterRegistrationBean(JwtFilter2 jwtFilter){
        log.info("filter => {}" , "filterConfig");
        FilterRegistrationBean<JwtFilter2> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(jwtFilter);
        filterReg.addUrlPatterns("/api/student2/update.json");
        filterReg.addUrlPatterns("/api/student2/delete.json");
        return filterReg;
    }


    @Bean //서버가 구동될때 자동으로 호출됨
    // JwtFilter2필터가 적용되는 url을 설정
    public FilterRegistrationBean<UrlFilter> filterRegistrationBean1(UrlFilter jwtFilter){
        log.info("filter => {}" , "filterConfig");
        FilterRegistrationBean<UrlFilter> filterReg = new FilterRegistrationBean<>();
        filterReg.setFilter(jwtFilter);
        filterReg.addUrlPatterns("/api/student2/*"); // * 은 전체 url (만들기만하면 기본설정됨.)
        return filterReg;
    }
}
