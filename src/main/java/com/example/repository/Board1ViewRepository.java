package com.example.repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Board1View;

@Repository
public interface Board1ViewRepository extends JpaRepository<Board1View, Long> {

    // SELECT * FROM Board1View WHERE title = 1? AND no = 2?;
    List<Board1View> findByNoAndTitle(Long no, String title);

    // SELECT * FROM Board1View WHERE title = 1? OR no = 2?;
    List<Board1View> findByNoOrTitle(Long no, String title);

    // SELECT * FROM Board1View WHERE no IN 1?;
    List<Board1View> findByNoIn(Long[] no);

    // SELECT * FROM Board1View WHERE title IN 1?;
    List<Board1View> findByTitleIn(String[] title);

}