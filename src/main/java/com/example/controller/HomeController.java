package com.example.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// 백엔드 Restful api연동 가능자 + mysql, orcle, mongodb ... + jpa + mybatis
// 


@Controller
public class HomeController {

    // http://127.0.0.1:9090/ROOT/login.do
    @GetMapping(value = "/login.do")
    public String loginGET() {
        return "login";
    }

    // http://127.0.0.1:9090/ROOT/home.do
    @GetMapping(value = { "/home.do", "/" })
    public String homeGET(Model model, @AuthenticationPrincipal User user) { // 세션에 있는 정보 가져오기
        if (user != null) { // 로그인 되었음
            System.out.println(user.toString());
        }
        model.addAttribute("user", user);
        return "home";
    }

    // http://127.0.0.1:9090/ROOT/403page.do
    @GetMapping(value = "/403page.do")
    public String page403GET() {
        return "/error/403page";
    }

    

}
