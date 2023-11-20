package com.playdata.domain.article.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleKafka {
    private Long id;
    private String title;
    private String content;
    private Category category;
    public static ArticleKafka ArticleBuilder(Article article)
    {
        return ArticleKafka
                .builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .category(article.getCategory())
                .build();
    }
}
