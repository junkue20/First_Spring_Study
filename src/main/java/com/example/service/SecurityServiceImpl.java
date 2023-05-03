package com.example.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dto.Member;
import com.example.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements UserDetailsService {
    final String format = "SecurityServiceImpl => {}";
    final MemberMapper mMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(format, username);
        // 아이디를 전달해서 정보를 받아오고, 암호까지 받아온다.
        Member member = mMapper.selectMemberOne1(username);
        if (member != null) {
            return User.builder()
                    .username(member.getId())
                    .password(member.getPassword())
                    .roles(member.getRole())
                    .build();
        }

        return User.builder()
                .username("_")
                .password("_")
                .roles("_")
                .build();
    }

}
