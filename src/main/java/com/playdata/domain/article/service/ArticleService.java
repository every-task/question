package com.playdata.domain.article.service;

import com.playdata.config.exception.NoArticleById;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(ArticleRequest articleRequest)
    {
        articleRepository.save(articleRequest.toEntity());
    }
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleResponse> getAll()
    {
        List<Article> getArticles = articleRepository.findAll();
        return getArticles.stream().map(ArticleResponse::new).toList();
    }



}
