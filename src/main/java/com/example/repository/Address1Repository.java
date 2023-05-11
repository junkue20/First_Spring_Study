package com.example.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Address1;
import com.example.entity.Address1Projection;



// 저장소 생성 (JpaRepository에는 기본적인 crud 구현되어 있음.)
// 저장소에 이상이 있을 시 서버가 안돌아감!
@Repository
public interface Address1Repository extends JpaRepository<Address1, Long>{

    // 제너릭을 이용한 타입설정
    <T> List<T> findAllByOrderByNoDesc(Class<T> type);

    // SELECT a.no, a.address, m.id, m.name FROM address1 a, member1 m ORDER BY no DESC;
    List<Address1Projection> findAllByOrderByNoDesc();
    
    // SELECT .. WHERE address = ?
    List<Address1> findByAddress(String address);

    // SELECT .. WHERE address = ?
    List<Address1> findByPostcode(String postcode);

    // SELECT .. WHERE address = ? AND postcode = ?
    List<Address1> findByAddressAndPostcode(String address, String postcode);

    // WHERE member1.id=? ORDER BY DESC  =>  Member1은 객체이기 때문에 '_'를 이용해서 id값 도출
    List<Address1> findByMember1_idOrderByNoDesc(String id);

    // WHERE member1.id=? ORDER BY DESC + 페이지네이션 기능 포함
    List<Address1> findByMember1_idOrderByNoDesc(String id, Pageable pageable);

    // SELECT count(*) FROM address1 WHERE member1.id=?
    long countByMember1_id(String id);

}
