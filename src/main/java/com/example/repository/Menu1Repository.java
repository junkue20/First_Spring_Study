package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Menu1;
import java.util.List;


@Repository
public interface Menu1Repository extends JpaRepository<Menu1, String>{
    
    //findBy 변수명_하위변수
    List<Menu1> findByRestaurant1_phone(String phone);

    // Menu1Projection
    // List<Menu1> findByRestaurant1_phone(String phone);
}
