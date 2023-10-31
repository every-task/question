package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleDetailResponse;
import com.playdata.domain.article.response.ArticleResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticleQueryDslRepository {
    Page<ArticleResponse> getArticleByCategory(PageRequest pageRequest, ArticleCategoryRequest articleCategoryRequest);

}
