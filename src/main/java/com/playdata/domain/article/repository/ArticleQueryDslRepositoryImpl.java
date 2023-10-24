package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.article.response.QArticleResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import static com.playdata.domain.article.entity.QArticle.article;

import java.util.List;

public class ArticleQueryDslRepositoryImpl implements ArticleQueryDslRepository{
    private JPAQueryFactory jpaQueryFactory;
    public ArticleQueryDslRepositoryImpl(EntityManager entityManager)
    {
        this.jpaQueryFactory=new JPAQueryFactory(entityManager);
    }


    @Override
    public List<ArticleResponse> getArticleByCategory(ArticleCategoryRequest articleCategoryRequest) {
        JPAQuery<ArticleResponse> query = (JPAQuery<ArticleResponse>) jpaQueryFactory.select(new QArticleResponse(article)).from(article)
                .where(isCatecory(articleCategoryRequest.getCatecory())).fetch();
        return (List<ArticleResponse>) query;
    }
    private BooleanExpression isCatecory(String catecory){
        return catecory==null ? null : article.content.contains(catecory);
    }
}
