package com.example.controller.mybatis;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dto.Member;
import com.example.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping( value = "/member" )
@Slf4j 
@RequiredArgsConstructor
public class MemberController {

    final HttpSession httpSession;
    final MemberMapper mMapper; // final MemberMapper mMapper;
   
    
    /* ------------------------------------------------------------------------------------------------- */
                                                // Join //

    @GetMapping (value="/join.do")
    public String joinGET() {
        log.info("member={}", "joinGET"); // system.out.prinln 말고 이걸 쓸 예정. "@Slf4j"
        return "/member/join";
    }


    @PostMapping (value="/join.do")
    public String joinPOST(@ModelAttribute Member member) {
        log.info("join.do POST => {]", member.toString());
        // 여기서 매퍼호출 후 회원가입하기
        int ret = mMapper.insertMemberOne(member);
        if( ret == 1 ) {
            return "redirect:/home.do"; // 성공시 홈으로
        } 
        else {
            return "redirect:/join.do";
        }
    }
    /* ------------------------------------------------------------------------------------------------- */
                                                // LogIn //

    @GetMapping(value="/login.do")
    public String loginGET() {
        return "/member/login";
    }
    

    @PostMapping(value="/login.do")
    public String loginPOST(@ModelAttribute Member member) {
        log.info("login.do => {}", member.toString()); // view에서 잘전송되었는지
        Member ret = mMapper.selectMemberOne(member); //로그인한 사용자의 정보 반환
        if( ret != null ) {
            log.info("login1.do => {}", ret.toString()); 
            // 세션에 2개의 정보 아이디와 이름 추가하기 ( 기본시간 30분 )
            // 다른페이지에서 세션의 아이디가 존재하는지 확인후 로그인 여부 판단
            httpSession.setAttribute("USERID", ret.getId());
            httpSession.setAttribute("USERNAME", ret.getName());
            httpSession.setAttribute("USERAGE", ret.getAge());
            httpSession.setAttribute("ROLE", ret.getRole());
            return "redirect:/home.do";    // 로그인 성공 시
        }
        return "redirect:login.do"; // 로그인 실패 시
    }
    
    /* ------------------------------------------------------------------------------------------------- */
                                                // LogOut //
    // GET, POST가 같은 동작을 함.
    @RequestMapping (value="/logout.do", method={RequestMethod.GET, RequestMethod.POST})
    public String logoutPOST() {
        httpSession.invalidate(); // 세션의 정보를 모두 지움.
        return "redirect:/home.do";
    }
    /* ------------------------------------------------------------------------------------------------- */
                                                // Info Update //
    @PostMapping(value="/update.do")
    public String updateMemberOne(@ModelAttribute Member member) {
        log.info("update.do => {}", member.toString());
        int ret = mMapper.updateMemberOne(member);
        if(ret == 1) {
        return "redirect:/home.do"; // 성공시
    }
    return ""; // 실패시
    }
     /* ------------------------------------------------------------------------------------------------- */
                                                // Delete Member //
    @PostMapping(value="path")
    public String deleteMemberInfo(@ModelAttribute Member member){
        log.info("delete.do => {}", member.toString() );
        int ret = mMapper.deleteMemberInfo(member);
        if(ret == 1) {
            return "redirect:/home.do"; // 탈퇴 성공시 메인페이지로
            // 추가로 세션 비우는 코드
        }
        return "redirect:delete.do"; // 
    };
}