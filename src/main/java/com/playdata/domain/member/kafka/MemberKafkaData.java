package com.playdata.domain.member.kafka;

import com.playdata.domain.member.entity.Member;

import java.util.UUID;

public record MemberKafkaData(UUID id,
                              String nickname,
                              String profileImageUrl) {

    public Member ToEntity() {
        return Member.builder()
                .id(id)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}