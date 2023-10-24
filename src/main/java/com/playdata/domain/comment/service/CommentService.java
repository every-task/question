package com.playdata.domain.comment.service;

import com.playdata.domain.comment.repository.CommentRepository;
import com.playdata.domain.comment.request.CommentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    public void insertComment(CommentRequest commentRequest)
    {
        commentRepository.save(commentRequest.toEntity());
    }

}
