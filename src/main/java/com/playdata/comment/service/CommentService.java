package com.playdata.comment.service;

import com.playdata.config.BaseEntity;
import com.playdata.config.TokenInfo;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.exception.NoArticleByIdException;
import com.playdata.exception.NotCorrectTokenIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    public void insertComment(CommentRequest commentRequest, Long articleId, UUID memberId)
    {
        Optional<Article> getArticleById = articleRepository.findById(articleId);
        getArticleById.orElseThrow(()->new NoArticleByIdException("error 500"));
        commentRepository.save(commentRequest.toEntity(memberId, articleId));
    }
    @Transactional
    public void deleteComment(TokenInfo tokenInfo, Long id)
    {
        Optional<Comment> getCommentById = commentRepository.findById(id);
        Comment comment=getCommentById.orElseThrow(()-> new NoArticleByIdException("error 500"));
        if(tokenInfo.getId().equals(comment.getMember().getId())) {
            comment.setMember(null);
            comment.setArticle(null);
            commentRepository.deleteById(id);
        }
        else {
            new NotCorrectTokenIdException("error 401");
        }
    }
    @Transactional
    public Comment updateComment(TokenInfo tokenInfo,Long id, CommentRequest commentRequest)
    {
        Optional<Comment> getCommentById = commentRepository.findById(id);
        Comment comment=getCommentById.orElseThrow(()->new NoArticleByIdException("error 500"));
        if(tokenInfo.getId().equals(comment.getMember().getId())) comment.setContent(commentRequest.content());
        else {
            new NotCorrectTokenIdException("error 401");
        }
        return comment;
    }


}
