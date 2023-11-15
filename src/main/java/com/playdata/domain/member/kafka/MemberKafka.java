package com.playdata.domain.member.kafka;

import com.playdata.domain.member.entity.Member;

import java.util.UUID;

public record MemberKafka(String id,
                              String nickname,
                              String profileImageUrl,
                          Action action) {

    public Member ToEntity() {
        return Member.builder()
                .id(UUID.fromString(id))
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
