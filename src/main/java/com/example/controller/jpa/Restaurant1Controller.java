package com.example.controller.jpa;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.Search;
import com.example.entity.Restaurant1;
import com.example.repository.Restaurant1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = "/restaurant1")
@RequiredArgsConstructor
public class Restaurant1Controller {

    final Restaurant1Repository r1Repository;
    final HttpSession httpSession;// 세션객체

    // 127.0.0.1:9090/ROOT/restaurant1/selectlist.food?page=1&type=phone&text=
    // 식당 전체목록 표시(페이지네이션, 연락처, 이름별, 종류별, 주소별 검색)
    @GetMapping(value = "/selectlist.food")
    public String selectlistGET(
        Model model, @ModelAttribute Search obj) {
            
        try {
            log.info("Restaurant1Controller => {}", obj.toString()); 
            // 페이지네이션
            PageRequest pageRequest = PageRequest.of(obj.getPage()-1,10);

            // 연락처 검색
            List<Restaurant1> list = r1Repository.findByPhoneContainingOrderByNoDesc(obj.getText(), pageRequest);
            long total = r1Repository.countByPhoneContaining(obj.getText());

            if (obj.getType().equals("name")){
                list = r1Repository.findByNameContainingOrderByNoDesc(obj.getText(), pageRequest);
                total = r1Repository.countByNameContaining(obj.getText());
            }
            else if(obj.getType().equals("address")){
                list = r1Repository.findByAddressContainingOrderByNoDesc(obj.getText(), pageRequest);
                total = r1Repository.countByAddressContaining(obj.getText());
            }
            else if(obj.getType().equals("type")) {
                list = r1Repository.findByTypeContainingOrderByNoDesc(obj.getText(), pageRequest);
                total = r1Repository.countByType(obj.getText());
            }
            model.addAttribute("list", list);
            model.addAttribute("pages", (total-1) + 1 / 10);
            model.addAttribute("search", obj);
            return "/restaurant1/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @GetMapping(value = "/insert.food" )
    public String insertGET() {
        try {
            return "/restaurant1/insert";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }

    @PostMapping(value = "/insert.food")
    public String insertPOST(
        @RequestParam(name = "phone") String phone,
        @RequestParam(name = "name") String name,
        @RequestParam(name = "address") String address,
        @RequestParam(name = "type") String type) {
        try { 
            Restaurant1 obj = new Restaurant1();
            // 식당 등록하기 기능
            obj.setPhone(phone);
            obj.setName(name);
            obj.setAddress(address);
            obj.setType(type);
            r1Repository.save(obj);
            return "redirect:/restaurant1/selectlist.food";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }
}
