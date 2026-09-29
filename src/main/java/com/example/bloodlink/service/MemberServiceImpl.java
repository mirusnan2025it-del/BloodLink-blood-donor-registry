package com.example.bloodlink.service;

import com.example.bloodlink.entity.Member;
import com.example.bloodlink.exception.EmailAlreadyExistsException;
import com.example.bloodlink.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    public MemberServiceImpl(MemberRepository repository,
                             PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member registerMember(Member member) {

        if (repository.existsByEmail(member.getEmail())) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        member.setPassword(
                passwordEncoder.encode(member.getPassword())
        );

        return repository.save(member);
    }

    @Override
    public Member getMemberByEmail(String email) {

        return repository.findByEmail(email).orElse(null);
    }
}