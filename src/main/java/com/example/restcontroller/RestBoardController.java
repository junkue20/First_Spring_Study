package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.Board;
import com.example.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// => html을 표시할 수 없음.
// Map, Member, board, List => 를 반환하면 자동으로 json으로 바꿔줌.

@RestController
@RequestMapping(value = "/api/board")
@RequiredArgsConstructor
@Slf4j

/* ------------Rest 네가지 방식------------ */
// GET => 조회
// POST => 추가, 로그인
// DELETE => 삭제
// PUT => 전체수정, PATCH => 일부수정
/* ---------------------------------------- */

public class RestBoardController {
    final String format = "RestBoardCont => {}";
    final BoardMapper bMapper; // 매퍼객체 생성

    /* -------------------------------------------------------- */

    // 127.0.0.1:9090/ROOT/api/board/updatehit.json
    @PutMapping(value = "/updatehit.json")
    public Map<String, Integer> updatehitPUT(@RequestBody Board board) {
        // 게시글 조회수 증가
        board.setHit(board.getHit() + 1);

        // DB에 추가하고 결과 0또는 1로 추가하기
        int ret = 1;
        Map<String, Integer> retMap = new HashMap<>(); // 확인용 Map
        retMap.put("result", ret);
        return retMap;
    }

    // 게시판 글쓰기
    // 127.0.0.1:9090/ROOT/api/board/insert.json
    @RequestMapping(value = "/insert.json", method = { RequestMethod.POST })
    public Map<String, Integer> insertPOST(@RequestBody Board board) {
        // 전송되는 값 확인
        log.info(format, board.toString());
        // DB에 추가하고 결과 0또는 1로 추가하기
        int ret = bMapper.insertBoard(board);

        Map<String, Integer> retMap = new HashMap<>();
        retMap.put("result", ret);
        return retMap;
    }

    // 127.0.0.1:9090/ROOT/api/board/selectlist.json
    @RequestMapping(value = "/selectlist.json", method = { RequestMethod.GET })
    // 여러개 사용가능 @RequestMapping
    public List<Board> requestMethodName() {
        // {"result":1 list:[{},{},{} ...{}]} => javascript
        return bMapper.selectBoard();
    }

    // 127.0.0.1:9090/ROOT/api/board/select.json
    @GetMapping(value = "/select.json")
    // 하나만 사용 가능 @GetMapping
    public Map<String, String> selectGET() {
        Map<String, String> retMap = new HashMap<>();
        retMap.put("result", "ok");
        return retMap;
    }
    /* -------------------------------------------------------- */
}
