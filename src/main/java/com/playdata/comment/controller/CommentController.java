package com.playdata.comment.controller;

import com.playdata.config.TokenInfo;
import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/question")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{articleId}")
    public void insertComment(@AuthenticationPrincipal TokenInfo tokenInfo, @PathVariable(value = "articleId") Long articleId, @RequestBody CommentRequest commentRequest)
    {
        commentService.insertComment(commentRequest,articleId,tokenInfo.getId());
    }


}
