package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long>,ArticleQueryDslRepository {


}
