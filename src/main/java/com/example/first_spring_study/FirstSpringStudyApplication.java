package com.example.first_spring_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages={"com.example.controller"}) // 컨트롤러 위치 설정
public class FirstSpringStudyApplication {

	// 서버 키고 끄는건 사용할때와 끝마칠때 한번씩만 누르면 됨!
	public static void main(String[] args) {
		SpringApplication.run(FirstSpringStudyApplication.class, args);
	}
}
