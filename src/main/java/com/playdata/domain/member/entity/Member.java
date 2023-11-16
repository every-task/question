package com.playdata.domain.member.entity;

import com.playdata.config.BaseEntity;
import com.playdata.domain.article.entity.Article;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Member extends BaseEntity{
    @Id
    private UUID id;
    private String nickname;
    private String profileImageUrl;
    @OneToMany(mappedBy = "member")
    private List<Article> articles;
}
