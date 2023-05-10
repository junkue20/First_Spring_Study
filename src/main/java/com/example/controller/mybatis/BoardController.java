package com.example.controller.mybatis;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.Board;
import com.example.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping(value = "/board") // 공통적인 앞(Mapping) 주소
@RequiredArgsConstructor // 클래스에서만 가능함, 인터페이스에서는 안됨
public class BoardController {

    // Autowired로 boardservice에 연결! (boardservice에서는 mapper와 연결됨.)
    final BoardService bService; // @Autowired BoardService bService;

    /* ---------------------- GetMapping ----------------------- */

    // 게시글 조회화면
    // 127.0.0.1:9090/ROOT/board/select.do
    @GetMapping(value = "/select.do")
    public String selectGET(Model model) {
        List<Board> list = bService.selectBoardList();
        model.addAttribute("list", list);

        // templates에서 board폴더를 생성하고 select.html생성
        return "/board/select"; // html 위치
    }

    // 게시글 입력화면
    // 127.0.0.1:9090/ROOT/board/insert.do
    @GetMapping(value = "/insert.do")
    public String insertGET() {
        return "/board/insert";
    }

    // 게시글 수정화면
    // 127.0.0.1:9090/ROOT/board/update.do
    @GetMapping(value = "/update.do")
    public String updateGET(
        Model model, 
        @RequestParam(name = "no", defaultValue = "0", required = false) long no) {
        if(no == 0) {
            return "redirect:select.do";
        }
        Board obj = bService.selectBoardOne(no);
        model.addAttribute("obj" ,obj);
        return "/board/update";
    }

    // // 게시글 상세조회 화면
    // // 127.0.0.1:9090/ROOT/board/selectone.do
    @GetMapping(value = "/selectone.do")
    public String selectoneGET(
        Model model, 
        @RequestParam(name = "no", defaultValue = "0", required = false) long no) {
        if(no == 0) {
            return "redirect:select.do";
        }
        Board obj = bService.selectBoardOne(no);
        model.addAttribute("obj" ,obj); // key를 생략했음, 변수명 obj가 키값임.

        return "/board/selectone"; // board폴더에 selectone.html 생성하기
    }

    /* ---------------------- PostMapping ---------------------- */

    // 게시글 입력
    @PostMapping(value = "/insert.do")
    public String insertPost(@ModelAttribute Board board) {
        System.out.println(board.toString());

        // DB에 추가
        int ret = bService.insertBoardOne(board);
        if (ret == 1) {// 성공 시
            // 적절한 페이지로 이동 (게시판 목록으로)
            return "redirect:select.do";
        }
        // 실패시
        return "redirect:insert.do";
    }

    @PostMapping(value = "/update.do")
    public String updatePost(@ModelAttribute Board board) {
        System.out.println(board.toString());
        
        // DB에 추가
        int ret = bService.updateBoardOne(board);
        if (ret == 1) {// 성공 시
            // 적절한 페이지로 이동 (게시판 목록으로)
            return "redirect:select.do";
        }
        // 실패시
        return "redirect:update.do?no="+board.getNo();
    }

    @PostMapping(value = "/delete.do")
    public String deletePost(@RequestParam(name = "no", defaultValue = "0", required= false) long no) {
        System.out.println(no);
        if(no == 0){
            return "redirect:select.do";
        }
        // DB에 추가
        int ret = bService.deleteBoardOne(no);
        if (ret == 1) {// 성공 시
            // 적절한 페이지로 이동 (게시판 목록으로)
            return "redirect:select.do";
        }
        // 실패시
        return "redirect:update.do?no="+no;
    }

}
