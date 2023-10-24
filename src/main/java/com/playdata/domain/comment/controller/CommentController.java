package com.playdata.domain.comment.controller;

import com.playdata.domain.comment.request.CommentRequest;
import com.playdata.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/question/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    public void insertComment(@RequestBody CommentRequest commentRequest)
    {

    }

}
