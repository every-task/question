package com.playdata.member.service;

import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.domain.member.request.MemberRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public void memberSave(MemberRequest memberRequest) {
        memberRepository.save(memberRequest.ToEntity());
    }
}
