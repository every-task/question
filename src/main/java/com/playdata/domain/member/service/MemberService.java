package com.playdata.domain.member.service;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.kafka.MemberKafkaData;
import com.playdata.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberKafkaData memberKafkaData)
    {
        memberRepository.save(memberKafkaData.ToEntity());
    }
    public Optional<Member> findById(UUID uuid)
    {
        return memberRepository.findById(uuid);
    }
}
