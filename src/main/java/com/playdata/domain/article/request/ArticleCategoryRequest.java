package com.playdata.domain.article.request;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ArticleCategoryRequest {
    private String category;
}
