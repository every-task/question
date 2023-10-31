package com.playdata.domain.comment.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.comment.dto.CommentDto;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.entity.Member;
import lombok.Getter;


@Getter
public class CommentResponse extends CommentDto {
    private Member member;




    public CommentResponse(Comment comment) {
        super(comment);
        this.member=comment.getMember();
    }


}
