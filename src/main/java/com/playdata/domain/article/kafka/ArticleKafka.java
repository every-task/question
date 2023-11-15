package com.playdata.domain.article.kafka;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.member.kafka.Action;
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
    private Action action;
    public static ArticleKafka ArticleBuilder(Article article, Action action)
    {
        return ArticleKafka
                .builder()
                .id(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .action(action)
                .build();
    }
}
