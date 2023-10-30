package com.playdata.domain.article.repository;

import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleResponse;

import com.playdata.domain.article.response.QArticleResponse;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;

import java.util.List;

import static com.playdata.domain.article.entity.QArticle.article;


public class ArticleQueryDslRepositoryImpl implements ArticleQueryDslRepository{
    private JPAQueryFactory jpaQueryFactory;
    public ArticleQueryDslRepositoryImpl(EntityManager entityManager)
    {
        this.jpaQueryFactory=new JPAQueryFactory(entityManager);
    }


    @Override
    public List<ArticleResponse> getArticleByCategory(ArticleCategoryRequest articleCategoryRequest) {
        JPAQuery<ArticleResponse> query = (JPAQuery<ArticleResponse>) jpaQueryFactory
                .select(new QArticleResponse(article))
                .from(article)
                .where(isCategory(articleCategoryRequest.getCatecory())).fetch();
        return (List<ArticleResponse>) query;
    }
    private BooleanExpression isCategory(String category){
        return category==null ? null : article.content.contains(category);
    }
}
