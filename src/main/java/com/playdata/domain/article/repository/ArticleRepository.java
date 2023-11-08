package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleQueryDslRepository {
//    @Query("select a.id,a.content,a.category,a.title, m.nickname,m.profileImageUrl from Article " +
//            "as a left join a.member as m on a.member.id = m.id" +
//            " where a.id=:id and a.deletedAt=null")
//    Optional<Article> findArticleById(Long id);
    Optional<Article> findByIdAndAndDeletedAtIsNull(Long id);

}
