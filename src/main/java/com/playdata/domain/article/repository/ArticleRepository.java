package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleQueryDslRepository {
//    @Query("select a from Article " +
//            "as a"+
//            " where a.id=:id and a.deletedAt is null")
//    Optional<Article> findArticleById(Long id);
    Optional<Article> findByIdAndAndDeletedAtIsNull(Long id);


}
