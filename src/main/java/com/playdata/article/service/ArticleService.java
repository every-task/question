package com.playdata.article.service;

import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        //send 실패
        questionProducer.send(ArticleKafka.of(save));
    }

    public Page<ArticleResponse> getAll(PageRequest pageRequest, ArticleCategoryRequest articleCategoryRequest)
    {
        return articleRepository.getArticles(pageRequest,articleCategoryRequest);
    }



    // id로 article 찾아옴
    public Article findById(Long id)
    {
        Optional<Article>  findAriticleById = articleRepository.findByIdAndAndDeletedAtIsNull(id);
        Article article = findAriticleById.orElseThrow(()-> new NoArticleByIdException("No Article"));
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
        }
        else {
            throw new NotCorrectTokenIdException("Not Correct Token");
        }
    }
    //update
    @Transactional
    public ArticleResponse updateArticle(TokenInfo tokenInfo,Long id,ArticleRequest article) throws NotCorrectTokenIdException
    {
        Article articleById = findById(id);
        if(tokenInfo.getId().equals(articleById.getMember().getId()))
        {
            articleById.setContent(article.getContent());
            articleById.setTitle(article.getTitle());
            articleById.setCategory(article.getCategory());
            questionProducer.send(ArticleKafka.of(articleById));
            return new ArticleResponse(articleById);
        }
        else {
            throw new NotCorrectTokenIdException("Not Correct Token");
        }
    }


}
