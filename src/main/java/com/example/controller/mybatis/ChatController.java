package com.example.controller.mybatis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ChatController {

    // http://127.0.0.1:9090/ROOT/chat.do
    @GetMapping(value = "/chat.do")
    public String chatGET() {
        return "/chat";
    }

}
