package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleRequest {
    private String content;
    private String title;
    public Article toEntity()
    {
        return Article
                .builder()
                .content(content)
                .title(title)
                .build();
    }

}
