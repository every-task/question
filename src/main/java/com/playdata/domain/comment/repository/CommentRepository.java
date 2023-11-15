package com.playdata.domain.comment.repository;

import com.playdata.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment as c " +
            "join fetch c.member where c.article.id=:id and c.deletedAt is null")
    List<Comment> findCommentsByArticleId(Long id);




}
