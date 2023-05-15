package com.example.controller.jpa;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.entity.library.Student2;
import com.example.repository.Student2Repository;




@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/student2")
public class Student2Controller {
    
    final String format = "s2Controller => {}";
    final Student2Repository s2Repository;
    BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();

    // 
    @GetMapping(value="/insert.do")
    public String insertGET() {
        try {
            return "/student2/insert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    

    // http://127.0.0.1:9090/ROOT/student2/insert.do
    @PostMapping(value="/insert.do")
    public String insertPOST(@ModelAttribute Student2 obj) {
        try {
            // 암호는 bcpe를 이용하여 암호화하기
            obj.setPassword(bcpe.encode( obj.getPassword()));
            log.info(format, obj.toString());
            s2Repository.save(obj);
            return "redirect:/student2/insert.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
    
    
}
