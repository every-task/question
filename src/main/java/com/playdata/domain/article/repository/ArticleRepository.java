package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {


//    @Query("select a from Article as a where a.id = :id")
//    Optional<Article> findArticleByIdToDelete(Long id);

}
