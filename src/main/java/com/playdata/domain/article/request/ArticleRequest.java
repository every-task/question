package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleRequest {
    private String content;
    private String title;
    public Article toEntity(UUID memberId)
    {
        return Article
                .builder()
                .content(content)
                .title(title)
                .member(Member.builder().id(memberId).build())
                .build();
    }

}