package com.playdata.article.service;

import com.playdata.config.TokenInfo;
import com.playdata.exception.NoArticleByIdException;
import com.playdata.exception.NotCorrectTokenIdException;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleDetailResponse;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.kafka.QuestionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //send 실패
        questionProducer.send(ArticleKafka.of(save));
    }

    public Page<ArticleResponse> getAll(PageRequest pageRequest, ArticleCategoryRequest articleCategoryRequest)
    {
        return articleRepository.getArticleByCategory(pageRequest,articleCategoryRequest);
    }



    // id로 article 찾아옴
    public Article findById(Long id)
    {
        Optional<Article> isIdNull = articleRepository.findById(id);
        Article article =isIdNull.orElseThrow(()->new NoArticleByIdException("error 500"));
        return article;
    }
//    상세 article
    public ArticleDetailResponse getById(Long id)
    {
        Article article =findById(id);
        return new ArticleDetailResponse(article);
    }

    @Transactional
    public void deleteById(TokenInfo tokenInfo, Long id) throws NotCorrectTokenIdException
    {
        Article article = findById(id);
        if(tokenInfo.getId().equals(article.getMember().getId()))
        {
            article.delete();
            articleRepository.deleteById(article.getId());
        }
        else {
            throw new NotCorrectTokenIdException("error 401");
        }
    }
    //update
    @Transactional
    public ArticleResponse updateArticle(TokenInfo tokenInfo,Long id,ArticleRequest article) throws NotCorrectTokenIdException
    {
        Article article1 = findById(id);
        if(tokenInfo.getId().equals(article1.getMember().getId()))
        {
            article1.setContent(article.getContent());
            article1.setTitle(article.getTitle());
            article1.setCategory(article.getCategory());
            return new ArticleResponse(article1);
        }
        else {
            throw new NotCorrectTokenIdException("error 401");
        }
    }


}
