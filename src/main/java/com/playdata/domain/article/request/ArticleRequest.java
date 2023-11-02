package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
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
    private Category category;
    public Article toEntity(UUID memberId)
    {
        return Article
                .builder()
                .content(content)
                .title(title)
                .category(category)
                .member(Member.builder().id(memberId).build())
                .build();
    }

}
