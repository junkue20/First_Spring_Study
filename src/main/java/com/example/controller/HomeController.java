package com.example.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    // 자동 임포트 shift + alt + o
    // 127.0.0.1:9090/ROOT/home.do
    // 127.0.0.1:9090/ROOT/
    @GetMapping(value = {"/home.do","/"})
    public String homeGET(Model model) {
        // requet.setAttribute("key","value")
        model.addAttribute("title", "전송된 타이틀");
        model.addAttribute("abc",   "마음대로");
        // request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request.response);
        return "home";
    }

    // 127.0.0.1:9090/ROOT/main.do
    @GetMapping(value = "/main.do")
    public String mainGET() {
        // request.getRequestDispatcher("/WEB-INF/main.jsp").forward(request.response);
        return "main";
    }


}
