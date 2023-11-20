package com.playdata.article.service;

import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.member.kafka.Action;
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
    private final CommentRepository commentRepository;
    private final QuestionProducer questionProducer;

    public void insert(ArticleRequest articleRequest, UUID memberId) {
        Article save = articleRepository.save(articleRequest.toEntity(memberId));
        questionProducer.sendArticleCreate(ArticleKafka.ArticleBuilder(save));
    }

    public Page<ArticleResponse> getAll(PageRequest pageRequest, ArticleCategoryRequest articleCategoryRequest) {
        return articleRepository.getArticles(pageRequest,articleCategoryRequest);
    }

    public Article findById(Long id) {
        Optional<Article>  findAriticleById = articleRepository.findArticleById(id);
        Article article = findAriticleById.orElseThrow(()->
                new NoArticleByIdException("No Article . id = {%s}".formatted(String.valueOf(id))));
        return article;
    }
    public ArticleDetailResponse getById(Long id) {
        Article article =findById(id);
        List<Comment> commentList = commentRepository.findCommentsByArticleId(id);
        article.setComments(commentList);
        return new ArticleDetailResponse(article);
    }

    @Transactional
    public void deleteById(TokenInfo tokenInfo, Long id) throws NotCorrectTokenIdException {
        Article article = findById(id);
        if(checkTokenAvailabiltiy(tokenInfo, article))
            article.delete();
        questionProducer.sendArticleDelete(ArticleKafka.ArticleBuilder(article));
    }

    public boolean checkTokenAvailabiltiy(TokenInfo tokenInfo, Article article)
            throws NotCorrectTokenIdException {
        if(!tokenInfo.getId().equals(article.getMember().getId()))
            throw new NotCorrectTokenIdException("Not Correct Token . token id = {%s}"
                    .formatted(String.valueOf(tokenInfo.getId())));
        return true;
    }

    @Transactional
    public ArticleResponse updateArticle(TokenInfo tokenInfo,Long id,ArticleRequest article)
            throws NotCorrectTokenIdException {
        Article articleById = findById(id);
        if(checkTokenAvailabiltiy(tokenInfo,articleById))
            articleById.setContent(article.getContent());
            articleById.setTitle(article.getTitle());
            articleById.setCategory(article.getCategory());
            questionProducer.sendArticleUpdate(ArticleKafka.ArticleBuilder(articleById));
            return new ArticleResponse(articleById);
    }
}
