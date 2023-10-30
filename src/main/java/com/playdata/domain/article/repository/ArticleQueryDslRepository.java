package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticleQueryDslRepository {
    List<ArticleResponse> getArticleByCategory(ArticleCategoryRequest articleCategoryRequest);

}
