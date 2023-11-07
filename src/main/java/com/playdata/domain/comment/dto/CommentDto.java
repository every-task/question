package com.playdata.domain.comment.dto;

import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.dto.MemberDto;
import lombok.Getter;

@Getter
public class CommentDto {
    private Long id;
    private String content;
    private MemberDto member;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.member=new MemberDto(comment.getMember());
    }
}
