package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleQueryDslRepository {
    @Query("select a from Article as a " +
            "join fetch a.member m where a.id=:id and a.deletedAt is null")
    Optional<Article> findArticleById(Long id);


}
