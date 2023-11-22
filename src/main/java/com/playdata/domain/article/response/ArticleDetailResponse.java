package com.playdata.domain.article.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.dto.CommentDto;
import com.playdata.domain.comment.entity.Comment;
import com.playdata.domain.member.dto.MemberDto;
import lombok.Getter;


import java.util.List;


@Getter
public class ArticleDetailResponse extends ArticleDto {

    private List<CommentDto> comments;
    private MemberDto member;

    public ArticleDetailResponse(Article article, List<Comment> commentList) {
        super(article);
        this.member=new MemberDto(article.getMember());
        this.comments= commentList.stream().
                        map(CommentDto::new).toList();
    }
}
