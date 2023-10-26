package com.playdata.article.service;

import com.playdata.config.exception.NoArticleById;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.kafka.QuestionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final QuestionProducer questionProducer;

    public void insert(ArticleRequest articleRequest, UUID memberId)
    {
        Article save = articleRepository.save(articleRequest.toEntity(memberId));
        questionProducer.send(ArticleKafka.of(save));
    }


    public List<ArticleResponse> getAll()
    {
        List<Article> getArticles = articleRepository.findAll();
        return getArticles.stream().map(ArticleResponse::new).toList();
    }



//    public List<ArticleResponse> getByCategory(ArticleCategoryRequest articleCategoryRequest)
//    {
//        List<ArticleResponse> getArticleByCategory = articleRepository.getArticleByCategory(articleCategoryRequest);
//        return getArticleByCategory;
//
//    }
    // id로 article 찾아옴
    public Article findById(Long id)
    {
        //예외처리 없을 경우
        Optional<Article> isIdNull = articleRepository.findById(id);
        Article article =isIdNull.orElseThrow(()->new NoArticleById("회원이 없습니다."));
        return article;
    }
//    상세 article
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
    public Article updateArticle(Long id,ArticleRequest article)
    {
        Article article1 = findById(id);
        article1.setContent(article.getContent());
        article1.setTitle(article.getTitle());
        return article1;
    }


}
