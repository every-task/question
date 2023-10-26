package com.playdata.domain.article.service;

import com.playdata.config.exception.NoArticleById;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ArticleResponse> getByCategory(ArticleCategoryRequest articleCategoryRequest)
    {
        List<ArticleResponse> getArticleByCategory = articleRepository.getArticleByCategory(articleCategoryRequest);
        return getArticleByCategory;

    }
    // id로 article 찾아옴
    public Article findById(Long id)
    {
        //예외처리 없을 경우
        Optional<Article> isIdNull = articleRepository.findById(id);
        Article article =isIdNull.orElseThrow(()->new NoArticleById("회원이 없습니다."));
        return article;
    }
//    상세 article
    @ResponseStatus(HttpStatus.OK)
    public ArticleResponse getById(Long id)
    {
        Article article =findById(id);
        return new ArticleResponse(article);
    }

    public void deleteById(Long id)
    {
        Article article = findById(id);
        articleRepository.deleteById(article.getId());
    }
    //update
    @ResponseStatus(HttpStatus.OK)
    public Article updateArticle(Long id,ArticleRequest article)
    {
        Article article1 = findById(id);
        article1.setContent(article.getContent());
        article1.setTitle(article.getTitle());
        return article1;
    }


}