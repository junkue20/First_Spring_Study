package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Restaurant1;


@Repository
public interface Restaurant1Repository extends JpaRepository<Restaurant1, String>{

    // SELECT * FROM restaurant1 WHERE phone LIKE '% || ? || %' ORDER BY no DESC;
    List<Restaurant1> findByPhoneContainingOrderByNoDesc(String phone, Pageable pageable);

    // SELECT COUNT(*) FROM restaurant1 WHERE phone LIKE '% || ? || %'
    long countByPhoneContaining(String phone);

    //--------------------------------------------------------------------------------------//

    // SELECT * FROM restaurant1 WHERE name LIKE '% || ? || %' ORDER BY no DESC;
    List<Restaurant1> findByNameContainingOrderByNoDesc(String name, Pageable pageable);

    // SELECT COUNT(*) FROM restaurant1 WHERE name LIKE '% || ? || %'
    long countByNameContaining(String name);

    //--------------------------------------------------------------------------------------//

    // SELECT * FROM restaurant1 WHERE type LIKE '% || ? || %' ORDER BY no DESC;
    List<Restaurant1> findByTypeContainingOrderByNoDesc(String type, Pageable pageable);

    // SELECT COUNT(*) FROM restaurant1 WHERE TYPE=?
    long countByType(String type);

    //--------------------------------------------------------------------------------------//

    // SELECT * FROM restaurant1 WHERE address LIKE '% || ? || %' ORDER BY no DESC;
    List<Restaurant1> findByAddressContainingOrderByNoDesc(String address, Pageable pageable);

    // SELECT COUNT(*) FROM restaurant1 WHERE address LIKE '% || ? || %'
    long countByAddressContaining(String address);

    //--------------------------------------------------------------------------------------//
}
