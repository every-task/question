package com.playdata.domain.comment.dto;

import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.dto.MemberDto;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentDto {
    private Long id;
    private String content;
    private MemberDto member;
    protected LocalDateTime createdAt;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.member=new MemberDto(comment.getMember());
        this.createdAt=comment.getCreatedAt();
    }
}
