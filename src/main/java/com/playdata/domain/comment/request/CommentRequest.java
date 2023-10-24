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
    private String content;
    private Member member;
    private Article article;

    public Comment toEntity()
    {
        return Comment
                .builder()
                .content(content)
                .article(article)
                .member(member)
                .build();
    }
}
