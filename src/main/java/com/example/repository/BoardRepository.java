package com.example.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, BigDecimal> {

    // SELECT * FROM board ORDER BY no DESC;
    List<Board> findAllByOrderByNoDesc();


    /*----------------- 3. 검색어 타입에 따른 메소드 3개 만들기 -------------------*/

    // SELECT * FROM board WHERE title LIKE '% || ? || %' ORDER BY no DESC;
    List<Board> findByTitleIgnoreCaseContainingOrderByNoDesc(String titleText);
    // IgnoreCase는 대소문자 구분없이 검색할 수 있게 해줌.

    // SELECT * FROM board WHERE content LIKE '% || ? || %' ORDER BY no DESC;
    List<Board> findByContentIgnoreCaseContainingOrderByNoDesc(String contentText);

    // SELECT * FROM board WHERE writer LIKE '% || ? || %' ORDER BY no DESC;
    List<Board> findByWriterIgnoreCaseContainingOrderByNoDesc(String writerText);


    // 페이지네이션
    List<Board> findByTitleIgnoreCaseContainingOrderByNoDesc(String titleText, Pageable pageable);
    List<Board> findByContentIgnoreCaseContainingOrderByNoDesc(String contentText, Pageable pageable);
    List<Board> findByWriterIgnoreCaseContainingOrderByNoDesc(String writerText, Pageable pageable);

    // 페이지네이션용 갯수 cnt
    long countByTitleIgnoreCaseContainingOrderByNoDesc(String titleText);
    long countByContentIgnoreCaseContainingOrderByNoDesc(String contentText);
    long countByWriterIgnoreCaseContainingOrderByNoDesc(String writerText);
}