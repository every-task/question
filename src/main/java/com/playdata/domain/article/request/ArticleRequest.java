package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Getter
public class ArticleRequest {
    private String content;
    private String title;
    private Category category;

    //test코드 용
    public ArticleRequest(String content, String title, Category category) {
        this.content=content;
        this.title=title;
        this.category=category;
    }

    public Article toEntity(UUID memberId) {
        return Article
                .builder()
                .content(content)
                .title(title)
                .category(category)
                .member(Member.builder().id(memberId).build())
                .build();
    }

}
