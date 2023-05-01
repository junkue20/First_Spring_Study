package com.example.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"password"})
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	  private String id; // 아이디(PK)
	  private String password; // 비번
	  private String name; // 이름
	  private int age; // 나이
	  private Date regdate = new Date(); // 고객등록일
	  private String newPassword;
	  private String role; // 고객은 customer, 판매자는 seller로 역할배정.

}
