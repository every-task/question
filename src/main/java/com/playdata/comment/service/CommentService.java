package com.playdata.comment.service;


import com.playdata.config.TokenInfo;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.domain.comment.response.CommentResponse;
import com.playdata.exception.NoArticleByIdException;
import com.playdata.exception.NoCommentByIdException;
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
        Optional<Article> getArticleById = articleRepository.findByIdAndAndDeletedAtIsNull(articleId);
        getArticleById.orElseThrow(()->
                new NoArticleByIdException("No Article . id = {%s}".formatted(String.valueOf(articleId))));
        commentRepository.save(commentRequest.toEntity(memberId, articleId));
    }
    public Comment findCommentById(Long id){
        Optional<Comment> getCommentById =commentRepository.findById(id);
        Comment comment= getCommentById.orElseThrow(()->
                new NoCommentByIdException("No Comment . id = {%s}".formatted(String.valueOf(id))));
        return comment;
    }
    
    @Transactional
    public void deleteComment(TokenInfo tokenInfo, Long id) throws NotCorrectTokenIdException {
        Comment comment=findCommentById(id);
        if(checkTokenAvailabiltiy(tokenInfo, comment)) comment.delete();
    }

    public boolean checkTokenAvailabiltiy(TokenInfo tokenInfo, Comment comment) throws NotCorrectTokenIdException {
        if(!tokenInfo.getId().equals(comment.getMember().getId()))
            throw new NotCorrectTokenIdException("Not Correct Token . token id = {%s}"
                    .formatted(String.valueOf(tokenInfo.getId())));
        return true;
    }

    @Transactional
    public CommentResponse updateComment(TokenInfo tokenInfo, Long id, CommentRequest commentRequest) throws NotCorrectTokenIdException {
        Comment comment=findCommentById(id);
        if(checkTokenAvailabiltiy(tokenInfo, comment)) comment.setContent(commentRequest.content());
        return new CommentResponse(comment);
    }


}
