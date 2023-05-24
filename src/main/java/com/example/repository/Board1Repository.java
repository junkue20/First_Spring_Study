package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.dto.Board1DTO;
import com.example.entity.Board1;

@Repository
public interface Board1Repository  extends JpaRepository<Board1, Long>{
    public List<Board1> findAllByOrderByNoDesc();
    

    // SQL + Projection => DTO 보관
    @Query(value = 
            " SELECT " + " new com.example.dto.Board1DTO(b1.no, b1.title, b1.writer) " + 
            " FROM Board1 b1 ORDER BY no DESC ")
    List<Board1DTO> selectBoardListProjection();

    
    @Query("SELECT b1,writer, COUNT(b1.hit) FROM Board1 b1 GROUP BY b1.writer")
    List<Object[]> selectWriterGrouptBy();
}