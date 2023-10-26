package com.playdata.domain.comment.response;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class CommentResponse {
    private Long id;
    private String content;
    private Member member;
    private Article article;


}
