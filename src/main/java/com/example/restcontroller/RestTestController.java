package com.example.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/test1")
@RequiredArgsConstructor
public class RestTestController {

    // 127.0.0.1:9090/ROOT/api/test1/select.do?aaa=a&bbb=b&ccc=c
    // 조회용 @RequestParam(name="aaa") String aaa
    @GetMapping(value = "/select.do")
    public Map<String, Object> get1(        ) {
        // log.info("받아온값 => {}");
        Map<String, Object> rMap = new HashMap<>();
        try {
            // db처리
            rMap.put("status", 200);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rMap;
    }

    // 추가
    // @RequestBody 엔티티 obj
    @PostMapping(value = "/insert.do")
    public Map<String, Object> get2(        ) {
        Map<String, Object> rMap = new HashMap<>();
        try {
            // db처리
            rMap.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rMap;
    }

    // 수정
    // 127.0.0.1:9090/ROOT/api/test1/update.do
    // @RequestBody 엔티티 obj
    @PutMapping(value = "/update.do")
    public Map<String, Object> get3(        ) {
        Map<String, Object> rMap = new HashMap<>();
        try {
            // db처리
            rMap.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rMap;

    }

    // 삭제
    // @RequestBody 엔티티 obj
    @DeleteMapping(value = "/delete.do")
    public Map<String, Object> get4(        ) {
        Map<String, Object> rMap = new HashMap<>();
        try {
            // db처리
            rMap.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rMap;
    }
}
