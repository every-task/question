package com.playdata.domain.comment.request;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;

import java.util.UUID;

public record CommentRequest(String content) {
    public Comment toEntity(UUID memberId, Long articleId) {
        return Comment.builder()
                .content(content)
                .member(Member.builder().id(memberId).build())
                .article(Article.builder().id(articleId).build())
                .build();
    }
}
