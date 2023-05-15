package com.example.controller.jpa;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.example.entity.library.Library2;
import com.example.repository.Library2Repository;



@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping(value = "/library2")
public class Library2Controller {
    
    final Library2Repository l2Repository;
    final String format = "Library2 => {}";


    @GetMapping(value = "/selectlist.do")
    public String selectlistGET(Model model) {
        try {
            List<Library2> list = l2Repository.findAllByOrderByNameAsc();

            // [클래스, 클래스]
            log.info(format, list.toString());
            model.addAttribute("list", list);
            return "/library2/selectlist";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }
    }


    @GetMapping(value="/insert.do")
    public String insertGET() {
        try {
            return "/library2/insert";
        } catch (Exception e) {
            e.printStackTrace();
        return "redirect:/home.do";
        }
    }
    

    @PostMapping(value="/insert.do")
    public String postMethodName(@ModelAttribute Library2 obj) {
        try {
            log.info("format", obj.toString());
            l2Repository.save(obj);
            return "redirect:/library2/insert.do";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/home.do";
        }        
        
    }
    
}
