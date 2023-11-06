package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleQueryDslRepository {
    @Query("select a.id,a.content,a.category,a.title,c.id,c.content, m.nickname,m.profileImageUrl from Article " +
            "as a inner join a.comments as c inner join a.member as m where a.id=:id")
    Optional<Article> findArticleById(Long id);


}
