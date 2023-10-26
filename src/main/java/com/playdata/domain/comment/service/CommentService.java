package com.playdata.domain.comment.service;

import com.playdata.config.exception.NoArticleById;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.domain.comment.response.CommentResponse;
import com.playdata.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    @PostMapping
    public void insertComment(CommentRequest commentRequest)
    {
        Optional<Article> isArticleNull = articleRepository.findArticleByIdToDelete(commentRequest.getArticleId());
        Article article=isArticleNull.orElseThrow(()->new RuntimeException("없음"));
        commentRepository.save(commentRequest.toEntity(article));
    }


}
