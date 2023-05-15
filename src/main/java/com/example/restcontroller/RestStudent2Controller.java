package com.example.restcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.library.Student2;
import com.example.entity.library.Student2Projection;
import com.example.repository.Student2Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/api/student2")
@RequiredArgsConstructor
public class RestStudent2Controller {
    
    final String format="RestStudent2 => {}";
    final Student2Repository s2Repository;
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    // 이메일 중복확인용
    // /ROOT/api/student2/emailcheck.json?email=이메일
    @GetMapping(value = "emailcheck.json")
    public Map<String, Object> emailcheckGET(@RequestParam(name = "email") String email) {
        Map<String, Object> retMap = new HashMap<>();
        try {
            long obj = s2Repository.countByEmail(email);
            retMap.put("status", 200);
            retMap.put("chk", obj);
        } catch (Exception e) {
            e.printStackTrace(); //
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }



    // 이메일이 전달되면 회원의 이름, 연락처가 반환되는
    @GetMapping(value="/selectone.json")
    public Map<String, Object> selectoneGET(@RequestParam(name = "email") String email) {
        Map<String, Object> retMap = new HashMap<>();
        try {
            Student2Projection obj2 = s2Repository.findByEmail(email);
            retMap.put("status", 200);
            retMap.put("obj", obj2);
        } catch (Exception e) {
            e.printStackTrace(); // 개발자용 debug
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }





    // 127.0.0.1:9090/ROOT/api/library2/selectlist.json
    @GetMapping(value="/selectlist.json")
    public Map<String, Object> selectListGET() {
        Map<String, Object> retMap = new HashMap<>();
        try {
            List<Student2> list = s2Repository.findAllByOrderByNameAsc();
            log.info(format, list.toString());
            retMap.put("status", 200);
            retMap.put("list", list);
        } catch (Exception e) {
            e.printStackTrace(); // 개발자용 debug
            retMap.put("status", -1);
            retMap.put("error", e.getMessage()); // 실패시 -1과 오류 전송
        }
        return retMap;
    }

    @PostMapping(value="/insert.json")
    public Map<String, Object> insertPOST(@RequestBody Student2 obj) { // insert 반환타입은 Map으로 해야함.
        Map<String, Object> retMap = new HashMap<>();
        try {
            obj.setPassword(bcpe.encode( obj.getPassword()));
            log.info(format, obj.toString());
            s2Repository.save(obj);
            retMap.put("status", 200);
        } catch (Exception e) {
            e.printStackTrace(); // 개발자용 debug
            retMap.put("status", -1);
            retMap.put("error", e.getMessage());
        }
        return retMap;
    }
}
