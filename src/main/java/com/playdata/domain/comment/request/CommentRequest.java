package com.playdata.domain.comment.request;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;

import java.util.UUID;

public record CommentRequest(String content) {
//    private Long articleId;

//
//    public Comment toEntity(Member member,Article article)
//    {
//        return Comment
//                .builder()
//                .content(content)
//                .article(article)
//                .member(member)
//                .build();
//    }
    //일단 임시로 member가 없어서 article만 추가
//    public Comment toEntity(Article article)
//    {
//        return Comment
//                .builder()
//                .content(content)
//                .article(article)
//                .build();
//    }

    public Comment toEntity(UUID memberId, Long articleId) {
        return Comment.builder()
                .content(content)
                .member(Member.builder().id(memberId).build())
                .article(Article.builder().id(articleId).build())
                .build();
    }
}
