package com.example.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.dto.CustomUser;
import com.example.dto.Member;
import com.example.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {

    final String format = "CustomerController => {}"; // 반복해서 log 입력을 안해도 됨!
    final MemberMapper mMapper;
    /* -------------------------------------------------------------- */
            // http://127.0.0.1:9090/ROOT/customer/home.do
                                // HOME //

    @GetMapping(value = "/home.do")
    public String homeGET(
        Model model,
        @AuthenticationPrincipal User user,
        @RequestParam(name = "menu", required = false, defaultValue = "0") int menu) {
        if(menu == 1) {
            // 세션에서 아이디정보를 꺼내서 mapper에서 조회
            Member member = mMapper.selectMemberOne1( user.getUsername() );
            log.info(format, member.toString());
            model.addAttribute("member", member);

            // 체크박스에 표시할 항목들
            String[] checkLabel = {"가가가", "가나다"," 나나나", "다다다", "가나다"};
            model.addAttribute("checklabel", checkLabel);
        }
        return "/customer/home";
    }

    // @AuthenticationPrincipal User user  => HttpSession httpSession => httpSession.getAttribute("user")
    @PostMapping(value = "/home.do")
    public String homePOST(@RequestParam(name="menu", required = false) int menu, 
                    HttpServletRequest request, @AuthenticationPrincipal CustomUser user,
                    HttpServletResponse response, @ModelAttribute Member member) {
        log.info(format, user.toString());
        log.info(format, menu);
        if(menu == 1) {
            member.setId(user.getUsername());
            int ret = mMapper.updateMemberOne(member);

            //아이디정보 가져오기 => user.getUsername();
            return "redirect:/customer/home.do?menu=1";
        }
        else if(menu == 2) {
            BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
            // 아이디 정보를 이용해서 dB에서 1명 조회
            // 조회된 정보의 암호와 사용자가 입력한 암호를 matches로 비교
            // 비밀번호 확인 => matches(바꾸기전비번, 해시된비번)
            if( bcpe.matches("hash 전 비번", "hash 후 비번") ) {

            }
            return "redirect:/customer/home.do?menu=2";
        }
        else if(menu == 3 ) {
            // 아이디 정보를 이용해서 db에서 1명 조회
            // 조회된 정보와 현재 암호가 일치하는지 matches로 비교
            // 비교가 true이면 db에서 삭제, 로그아웃

            // 컨트롤러에서 logout처리하기
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
            }
            return "redirect:/home.do";
        }

        return "redirect:/customer/home.do";
    }
    /* -------------------------------------------------------------- */
            // http://127.0.0.1:9090/ROOT/customer/join.do
                                // JOIN //

    @GetMapping(value = "/join.do")
    public String joinGET() {
        return "/customer/join";
    }

    @PostMapping(value = "/join.do")
    public String joinPOST(@ModelAttribute Member member) {
        log.info(format, member.toString());
        // 사용자가 입력한 항목을 member객체에 저장함을 확인하는용

        BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder(); // salt값을 자동부여
        member.setPassword(bcpe.encode(member.getPassword())); // 기존 패스워드를 암호화시켜서 다시 저장함.
        // eclipse에서 사용했던 hashPW 방식과 유사함.

        int ret = mMapper.insertMemberOne(member);
        if (ret == 1) { // 성공시 joinok 페이지로
            return "redirect:joinok.do"; // POST에서는 보통 "redirect:"를 사용.
        } // 실패시 다시 회원가입 페이지로
        return "redirect:join.do";
    }

    @GetMapping(value = "/joinok.do")
    public String joinokGET() {
        return "/customer/joinok";
    }

    /* -------------------------------------------------------------- */
}
