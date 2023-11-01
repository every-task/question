package com.playdata.domain.member.dto;

import com.playdata.domain.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberDto {
    private String nickname;
    private String profileImageUrl;

    public MemberDto(Member member) {
        this.nickname = member.getNickname();
        this.profileImageUrl = member.getProfileImageUrl();
    }
}
