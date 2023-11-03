package com.playdata.kafka;

import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.kafka.MemberKafkaData;
import com.playdata.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MemberConsumer {
    private final MemberRepository memberRepository;

    @KafkaListener(topics = TopicConfig.MEMBER)
    public void listen(MemberKafkaData data) {
        Optional<Member> member =memberRepository.findById(UUID.fromString(data.id()));
        if(member.isEmpty()) memberRepository.save(data.ToEntity());
        else{
            Member member1 = member.get();
            member1.setNickname(data.nickname());
            member1.setProfileImageUrl(data.profileImageUrl());
        }
    }
}
