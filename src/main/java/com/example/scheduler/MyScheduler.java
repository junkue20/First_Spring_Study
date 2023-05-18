package com.example.scheduler;

import java.util.Date;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.entity.Board1;
import com.example.repository.Board1Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyScheduler {

    final Board1Repository board1Repository;

    // cron tap
    // 초, 분, 시간, 일, 월, 요일
    // */5 * * * * * 5초 간격으로 동작
    // ex) */20 * * * * * 20초 간격동작 
    // ex) (0 * * * * *) 0 초가 될때 1분간격
    
    @Scheduled(cron = "*/5 * * * * *") 
    // 사용시 application에 @EnableScheduling 어노테이션 추가할 것 
    public void printDate() {
        log.info("{}", new Date().toString());

        List<Board1> list = board1Repository.findAll();
        log.info("{}", list.toString());
        
    }
    
}
