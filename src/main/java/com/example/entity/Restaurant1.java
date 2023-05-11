package com.example.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Data
@Table(name = "RESTAURANT1")
@SequenceGenerator(name = "SEQ_RESTAURANT1_NO", sequenceName = "SEQ_RESTAURANT1_NO", initialValue = 1, allocationSize = 1)
@IdClass(Restaurant1ID.class)
public class Restaurant1{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESTAURANT1_NO")
    private BigInteger no;

    @Id
    private String phone;

    private String name;

    private String address;

    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    @CreationTimestamp
    @Column(updatable = false)
    private Date regdate;

}