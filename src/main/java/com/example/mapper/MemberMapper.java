package com.example.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.Member;

@Mapper
public interface MemberMapper {

    // Join
    public int insertMemberOne(Member member);

    // LogIn
    public Member selectMemberOne(Member member);

    // Member Info Update
    public int updateMemberOne(Member member);

    // Member Info Update
    public Member selectMemberOne1(String username);

    // Member Delete
    public int deleteMemberInfo(Member member);
}
