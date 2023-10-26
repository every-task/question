package com.playdata.domain.comment.request;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequest {
    private Long articleId;
    private String content;

    public Comment toEntity(Member member,Article article)
    {
        return Comment
                .builder()
                .content(content)
                .article(article)
                .member(member)
                .build();
    }
    //일단 임시로 member가 없어서 article만 추가
    public Comment toEntity(Article article)
    {
        return Comment
                .builder()
                .content(content)
                .article(article)
                .build();
    }
}
