package com.example.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER1") //생성하고자하는 테이블, 또는 생성되어 있는 테이블 매칭
public class Member1 {
    
    @Id   //import javax.persistence.Id;
    @Column(name="ID",length = 50)
    private String id; //@Column을 생략하면 변수명이 컬럼명

    private String pw;

    private String name;

    private int age;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp // 추가시에만 날짜정보 저장
    private Date regdate;

    // EAGER => member1을 조회시 address1을 join하여 보여줌
    // LAZY => member1을 조회시 address1을 join하지 않고 address1을 필요할 때 조인함.

    /* 양방향 외래키 */
    @ToString.Exclude // stackoverflow
    @OneToMany(mappedBy = "member1", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @OrderBy(value = "no desc")
    List<Address1> list = new ArrayList<>();
 }