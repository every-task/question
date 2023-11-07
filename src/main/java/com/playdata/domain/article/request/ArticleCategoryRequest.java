package com.playdata.domain.article.request;

import com.playdata.domain.article.entity.Category;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ArticleCategoryRequest {
    private List<Category> category;
    private String keyword;
}
