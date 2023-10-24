package com.playdata.domain.article.entity;

import com.playdata.config.BaseEntity;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "article")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    private String category;

    @OneToMany(mappedBy = "article")
    private List<Comment> commentList;

    @ManyToOne
    private Member member;
}
