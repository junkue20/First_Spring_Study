package com.example.controller.jpa;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.entity.Board1View;
import com.example.repository.Board1ViewRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/board1view")
@RequiredArgsConstructor
public class Board1ViewController {

    final Board1ViewRepository b1vRepository;

    // num이 없으면 0 또는 전체
    // num이 1이면 and
    // num이 2이면 or
    // num이 3이면 글번호 in
    // num이 4이면 제목 in

    // 127.0.0.1:9090/ROOT/board1view/selectlist.pknu
    @GetMapping(value = "/selectlist.pknu")
    public String selectlistGET(
            Model model,
            @RequestParam(name = "num", defaultValue = "0", required = false) Long num,
            @RequestParam(name = "boardNo", defaultValue = "0", required = false) Long boardNo,
            @RequestParam(name = "boardTitle", defaultValue = "", required = false) String boardTitle,
            @RequestParam(name = "boardNo1", defaultValue = "", required = false) String boardNo1,
            @RequestParam(name = "boardTitle1", defaultValue = "", required = false) String boardTitle1
            ) {
        try {
            List<Board1View> list = b1vRepository.findAll();
            if (num == 0L) {
                
            } else if (num == 1L) {
               list = b1vRepository.findByNoAndTitle(boardNo, boardTitle);
            } else if (num == 2L) {
               list = b1vRepository.findByNoOrTitle(boardNo, boardTitle);
            } else if (num == 3L) {
                String[] arr = boardNo1.split(","); // split 활용, ","을 구분
                Long[] arrLong = new Long[arr.length];
                for(int i = 0; i<arr.length;i++){
                 arrLong[i] = Long.parseLong(arr[i]);
                }
                list = b1vRepository.findByNoIn(arrLong);
            } else if (num == 4L) { 
                String[] arr = boardTitle1.split(",");
                list = b1vRepository.findByTitleIn(arr);
            }
            model.addAttribute("list", list);
            return "/board1view/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }

    }

}
