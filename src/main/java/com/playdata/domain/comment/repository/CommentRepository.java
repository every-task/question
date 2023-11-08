package com.playdata.domain.comment.repository;

import com.playdata.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c.content ,m.nickname,m.profileImageUrl from Comment" +
            " as c inner join c.member as m on c.member.id=m.id where c.article.id=:id")
    List<Comment> findCommentsByArticleId(Long id);



}
