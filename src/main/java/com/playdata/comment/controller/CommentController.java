package com.playdata.comment.controller;

import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.comment.service.CommentService;
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
    @GetMapping("/{articleId}")
    public List<Comment> getAllCommentByArticleId(@PathVariable(value= "articleId")Long articleId)
    {
        return commentService.getAllCommentByArticleId(articleId);
    }
    @DeleteMapping("/{id}")
    public void deleteComment(@AuthenticationPrincipal TokenInfo tokenInfo,
                              @PathVariable(value="id") Long id)
    {
        commentService.deleteComment(tokenInfo,id);
    }
    @PutMapping("/{id}")
    public Comment updateComment(@AuthenticationPrincipal TokenInfo tokenInfo,
                                 @PathVariable(value = "id") Long id,
                                 CommentRequest commentRequest)
    {
        return commentService.updateComment(tokenInfo,id,commentRequest);
    }


}
