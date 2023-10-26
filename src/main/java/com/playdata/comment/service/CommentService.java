package com.playdata.comment.service;

import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    @PostMapping
    public void insertComment(CommentRequest commentRequest, Long articleId, UUID memberId)
    {
//        Optional<Article> isArticleNull = articleRepository.findArticleByIdToDelete(commentRequest.getArticleId());
//        Article article=isArticleNull.orElseThrow(()->new RuntimeException("없음"));
//        commentRepository.save(commentRequest.toEntity(article));
        commentRepository.save(commentRequest.toEntity(memberId, articleId));
    }


}
