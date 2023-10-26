package com.playdata.domain.member.entity;

import com.playdata.domain.article.entity.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//builder all args 추가함
@Builder
@AllArgsConstructor
public class Member {
    @Id
    private UUID id;

    private String nickname;
    private String profileImageUrl;

    @OneToMany(mappedBy = "member")
    private List<Article> articles;

    public Member(UUID id, String nickname, String profileImageUrl) {
        this.id=id;
        this.nickname=nickname;
        this.profileImageUrl=profileImageUrl;
    }
}
