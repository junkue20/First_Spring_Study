package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.library.Student2;
import com.example.entity.library.Student2Projection;

@Repository
public interface Student2Repository extends JpaRepository<Student2, String> {

    // select * from 테이블명 order by Name asc
    List<Student2> findAllByOrderByNameAsc();

    
    // select name, phone from student2 where email = ?
    Student2Projection findByEmail(String email);

    // 이메일 중복확인
    // select count(*) from student2 where email = ?
    long countByEmail(String email);
}
