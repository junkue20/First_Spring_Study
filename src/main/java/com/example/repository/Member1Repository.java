package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.Member1;
import com.example.entity.Member1Projection;


// 엔티티, 엔티티의기본키타입
@Repository
public interface Member1Repository extends JpaRepository<Member1, String>{

    // 일부 컬럼만 출력
    // JPQL => SELECT id, name, age FROM member1 ORDER BY id ASC;
    public List<Member1Projection> findAllByOrderByIdAsc();
    
    // JPQL => select m.* from Member1 m order by m.name desc;
    public List<Member1> findAllByOrderByNameDesc();

    // JPQL => select m.* from Member1 m where m.name like '%?%' order by m.name desc;
    public List<Member1> findByNameContainingOrderByNameDesc(String name);

    
    public long countByNameContaining(String name);

    // JPQL => select m.* from Member1 m where m.name like '%?%' order by m.name desc limit 페이지네이션
    public List<Member1> findByNameIgnoreCaseContainingOrderByNameDesc(String name, Pageable pageable);

    // nativequery사용하기
    @Query(value="SELECT * FROM ( SELECT m1.*, ROW_NUMBER() OVER (ORDER BY name DESC) rown FROM MEMBER1 m1 WHERE m1.name LIKE '%' || :name || '%' ) WHERE rown BETWEEN :start AND :end", nativeQuery=true)
    public List<Member1> selectByNameContainingPagenation(@Param("name") String name, @Param("start") int start, @Param("end") int end );
  

}