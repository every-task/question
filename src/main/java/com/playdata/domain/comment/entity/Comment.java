package com.playdata.domain.comment.entity;

import com.playdata.config.BaseEntity;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    private Member member;
    @ManyToOne
    private Article article;
}
