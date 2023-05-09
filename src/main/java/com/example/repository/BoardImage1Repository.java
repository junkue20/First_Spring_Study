package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.BoardImage1;

@Repository
public interface BoardImage1Repository extends JpaRepository<BoardImage1, Long>{

    // 추가, 수정, 삭제... 
    
    // 조회
    // 변수명 객체 Board1_no, No, Name Age
    // findBy 변수명 OrderBy변수명Asc

    // 게시글 번호가 일치하는 것중에서 이미지번호가 가장 적은것을 반환
    // SELECT * FROM boardimage1 WHERE board.no=13 ORDER BY no ASC LIMIT 1;
    BoardImage1 findTopByBoard1_noOrderByNoAsc(Long no);

    // 게시글번호가 일치하는 모든 이미지
    // SELECT * FROM boardimage1 WHERE board.no=13 ORDER BY no ASC;
    List<BoardImage1> findByBoard1_noOrderByNoAsc(Long no);
}
