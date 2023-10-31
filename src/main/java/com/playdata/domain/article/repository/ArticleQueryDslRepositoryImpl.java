package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleResponse;

import com.playdata.domain.article.response.QArticleResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.playdata.domain.article.entity.QArticle.article;


public class ArticleQueryDslRepositoryImpl implements ArticleQueryDslRepository{
    private JPAQueryFactory jpaQueryFactory;
    public ArticleQueryDslRepositoryImpl(EntityManager entityManager)
    {
        this.jpaQueryFactory=new JPAQueryFactory(entityManager);
    }



    @Override
    public Page<ArticleResponse> getArticleByCategory(PageRequest pageRequest, ArticleCategoryRequest articleCategoryRequest) {
        JPAQuery<ArticleResponse> query =jpaQueryFactory.select(new QArticleResponse(article))
                .from(article).where((isCategory(articleCategoryRequest.getCategory())
                        )
                ).offset(pageRequest.getPageNumber()* pageRequest.getPageSize()).limit(pageRequest.getPageSize());
        List<ArticleResponse> content = query.fetch();
        Long totalSize =jpaQueryFactory.select(article.count()).from(article).where(
                isCategory(articleCategoryRequest.getCategory())
        ).fetchOne();

        return new PageImpl(content,pageRequest,totalSize);
    }

    private BooleanBuilder isCategory(String category){
        return category==null ? null : categoryOrCondition(category);
    }

    private BooleanBuilder categoryOrCondition(String category) {
        BooleanBuilder builder = new BooleanBuilder();
        String[] strArr = category.split(","); // [s, a, m, p, l, e]
        ArrayList<String> categoryTypes = new ArrayList<String>(Arrays.asList(strArr));
        categoryTypes.stream().forEach(t -> builder.or(article.category.eq(t))); // 체크된 항목 or
        return categoryTypes==null? null : builder;
    }


}
