package com.playdata.domain.comment.dto;

import com.playdata.domain.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentDto {
    private Long id;
    private String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
    }
}
