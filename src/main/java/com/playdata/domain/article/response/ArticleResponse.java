package com.playdata.domain.article.response;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.member.entity.Member;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ArticleResponse {
    private String title;
    private String content;
    private Member member;

    @QueryProjection
    public ArticleResponse(Article article)
    {
        this.title=article.getTitle();
        this.content=article.getContent();
        this.member= article.getMember();
    }
}
