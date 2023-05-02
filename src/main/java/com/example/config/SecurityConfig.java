package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration // 환경설정파일. 서버가 구동되기전에 호출됨.
@EnableWebSecurity // 시큐리티를 사용
@Slf4j // 출력용 (log.info)
public class SecurityConfig {

    @Bean // 객체를 생성한다. (자동으로 호출됨.)
    // 설정 초기에는 모든 페이지를 로그인을 해야하는 페이지로 전환
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.info("SecurityConfig => {}", "start filter chain");
        // 로그인, 로그아웃, 권한설정 등등..

        return http.build();
    }
}
