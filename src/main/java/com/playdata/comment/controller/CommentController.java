package com.playdata.comment.controller;

import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.comment.service.CommentService;
import com.playdata.domain.comment.response.CommentResponse;
import com.playdata.exception.NotCorrectTokenIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/question/comment")
@RequiredArgsConstructor

public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{articleId}")
    public void insertComment(@AuthenticationPrincipal TokenInfo tokenInfo,
                              @PathVariable(value = "articleId") Long articleId,
                              @RequestBody CommentRequest commentRequest)
    {
        commentService.insertComment(commentRequest,articleId,tokenInfo.getId());
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@AuthenticationPrincipal TokenInfo tokenInfo,
                              @PathVariable(value="id") Long id) throws NotCorrectTokenIdException {
        commentService.deleteComment(tokenInfo,id);
    }
    @PutMapping("/{id}")
    public CommentResponse updateComment(@AuthenticationPrincipal TokenInfo tokenInfo,
                                         @PathVariable(value = "id") Long id,
                                         CommentRequest commentRequest) throws NotCorrectTokenIdException {
        return commentService.updateComment(tokenInfo,id,commentRequest);
    }


}
