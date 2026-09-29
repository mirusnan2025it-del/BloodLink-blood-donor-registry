package com.example.bloodlink.controller;

import com.example.bloodlink.dto.MemberResponse;
import com.example.bloodlink.entity.Member;
import com.example.bloodlink.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public MemberResponse registerMember(@RequestBody Member member) {

        Member savedMember = service.registerMember(member);

        return new MemberResponse(
                savedMember.getId(),
                savedMember.getFullName(),
                savedMember.getEmail(),
                savedMember.getPhone(),
                savedMember.getRole()
        );
    }
}