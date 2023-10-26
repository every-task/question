package com.playdata.domain.comment.controller;

import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/question/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping
    public void insertComment(@RequestBody CommentRequest commentRequest)
    {
        commentService.insertComment(commentRequest);
    }


}
