package com.example.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.entity.library.Student2;
import com.example.restcontroller.JwtUtil2;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

// 회원정보 수정, 회원탈퇴, 비밀번호 변경 등 토큰이 필요한 경우에 대한 처리
// 컨트롤러 전에 수행되는 클래스 => 토큰의 유효성을 검증하고 실패시 컨트롤러 진입 x
// url에 주소에 따라 분류함
@Component
@RequiredArgsConstructor
public class JwtFilter2 extends OncePerRequestFilter {

    final JwtUtil2 jwtUtil2;
    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, Object> retMap = new HashMap<>();


            String token = request.getHeader("token");
            if ( token == null ) { // 토큰 키 자체가 없을때
                // 못가게 함 + 오류메세지 출력
                retMap.put("status", -1);
                retMap.put("message", "token null");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);

                return; // 메소드 종료
            }
            
            if ( token.length() <= 0 ) { // 전송은 되었으나 토큰내용이 누락되었을 때
                retMap.put("status", -1);
                retMap.put("message", "token is empty");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);
                // 역시 내용이 없기때문에 못가게함 + 

                return; // 메소드 종료
            }
            
            Student2 obj = jwtUtil2.checkJwt(token);
            if( obj == null ) { // 토큰 전송이후 student2 객체값이 반환되지 않았을 때
                retMap.put("status", -1);
                retMap.put("message", "token error");
                String result = objectMapper.writeValueAsString(retMap);
                response.getWriter().write(result);

                return; // 메소드 종료
            }

            // 아래의 명령어가 실행되어야 정상적인 컨트롤러로 진입 가능.
            filterChain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(-1, "token error");
        }
    }

}
