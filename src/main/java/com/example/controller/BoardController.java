package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/board") // 공통적인 앞 주소
public class BoardController {

    // 게시판 조회
    // 127.0.0.1:9090/ROOT/board/select.do
    @GetMapping(value = "/select.do")
    public String selectGET() {
        return "/board/select"; // html 위치
    }

    // 게시글 입력  
    // 127.0.0.1:9090/ROOT/board/insert.do
    @GetMapping(value = "/insert.do")
    public String insertGET() {
        return "/board/insert";
    }

    // 게시글 업데이트
    // 127.0.0.1:9090/ROOT/board/update.do
    @GetMapping(value = "/update.do")
    public String updateGET() {
        return "/board/update";
    }
}
