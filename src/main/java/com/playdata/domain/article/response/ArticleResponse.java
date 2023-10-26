package com.playdata.domain.article.response;

import com.playdata.domain.article.dto.ArticleDto;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.dto.CommentDto;
import lombok.Getter;

import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
public class ArticleResponse extends ArticleDto {
//    private String title;
//    private String content;
//    private Member member;
//    private List<?> commentList;
    private List<CommentDto> comments;

//    @QueryProjection
    public ArticleResponse(Article article)
    {
        super(article);
        comments = article.getComments()
                .stream()
                .map(CommentDto::new)
                .toList();

//        this.title=article.getTitle();
//        this.content=article.getContent();
//        this.member= article.getMember();
//        this.commentList = article.getCommentList() != null ?
//                article.getCommentList()
//                        .stream()
//                        .map(CommentResponse::new).toList() :new ArrayList<>();

    }
}
