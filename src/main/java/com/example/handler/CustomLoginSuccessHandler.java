package com.example.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    final String format = "LoginSuccessHandler => {}";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException  {
        log.info(format, authentication.toString());

        String role = authentication.getAuthorities().toArray()[0].toString();
        log.info(format, role);

        HttpSession httpSession = request.getSession();
        String backUrl = (String) httpSession.getAttribute("url");

        // 필요시 이전페이지로 이동함.
        if(role.equals("ROLE_CUSTOMER")) { // 권한이 고객
        
            response.sendRedirect(request.getContextPath() + "/customer/home.do");
        }
        else if(role.equals("ROLE_SELLER")) { // 권한이 판매자
            response.sendRedirect(request.getContextPath() + "/seller/home.do");
        }
        else if(role.equals("ROLE_ADMIN")) { // 권한이 관리자
            response.sendRedirect(request.getContextPath() + "/admin/home.do");
        }
        else{
            response.sendRedirect(request.getContextPath() + "/home.do");
        }
    }

}

