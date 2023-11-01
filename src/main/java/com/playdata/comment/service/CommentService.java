package com.playdata.comment.service;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.exception.NoArticleByIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    @PostMapping
    public void insertComment(CommentRequest commentRequest, Long articleId, UUID memberId)
    {
        Optional<Article> isArticleNull = articleRepository.findById(articleId);
        isArticleNull.orElseThrow(()->new NoArticleByIdException("작성할 질문글이 없습니다."));
        commentRepository.save(commentRequest.toEntity(memberId, articleId));
    }


}
