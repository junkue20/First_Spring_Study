package com.example.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

// 인터셉터 => 컨트롤러 실행 전 후를 필터 가능
// 오버라이딩 시키는법 = 우클릭, [소스작업] , implements 클릭 후 사용할 메소드 불러오기
@Component
@Slf4j
public class HttpInterceptor implements HandlerInterceptor {

    // view 까지 끝난 이후
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    // Controller가 끝난 후
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        log.info(" ==================== postHandler ==================== ");
        log.info(request.getServletPath());
    }

    // Controller로 보내기 전에 처리되는 인터셉터 (boolean 타입)
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info(" ==================== preHandler ==================== ");
        log.info(request.getServletPath());

        return true; // 이 값이 false라면 controller로의 진입이 안됨.
    }

}
