package com.example.bloodlink.service;

import com.example.bloodlink.entity.Member;

public interface MemberService {

    Member registerMember(Member member);

    Member getMemberByEmail(String email);
}