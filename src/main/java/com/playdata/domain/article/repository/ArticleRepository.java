package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleQueryDslRepository {
    @Query("select a from Article as a join fetch a.commentList")
    Optional<Article> findArticleById(Long id);

    @Query("select a from Article as a where a.id = :id")
    Optional<Article> findArticleByIdToDelete(Long id);

}
