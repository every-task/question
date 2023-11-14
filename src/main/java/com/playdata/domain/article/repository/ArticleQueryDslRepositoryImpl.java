package com.playdata.domain.article.repository;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleResponse;


import com.playdata.domain.article.response.QArticleResponse;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.playdata.domain.article.entity.QArticle.article;


public class ArticleQueryDslRepositoryImpl implements ArticleQueryDslRepository{
    private JPAQueryFactory jpaQueryFactory;
    public ArticleQueryDslRepositoryImpl(EntityManager entityManager)
    {
        this.jpaQueryFactory=new JPAQueryFactory(entityManager);
    }



    @Override
    public Page<ArticleResponse> getArticles(PageRequest pageRequest, ArticleCategoryRequest articleCategoryRequest) {
        JPAQuery<Article> query =jpaQueryFactory.select(article)
                .from(article)
                .join(article.member)
                .fetchJoin()
                .where((findExistCategory(articleCategoryRequest.getCategory()))
                ,article.isDeleted.eq(false)
                ,(findExistKeywordInTitle(articleCategoryRequest.getKeyword())
                                .or(findExistKeyWordInContent(articleCategoryRequest.getKeyword()))
                                .or(findExistKeyWordInNickName(articleCategoryRequest.getKeyword()))))
                .orderBy(createOrderSpecifier(articleCategoryRequest.getOrderBy()))
                .offset(pageRequest.getPageNumber()* pageRequest.getPageSize())
                .limit(pageRequest.getPageSize());
                List<Article> content = query.fetch();
                Long totalSize =jpaQueryFactory.select(article.count())
                        .from(article)
                        .where((findExistCategory(articleCategoryRequest.getCategory()))
                                ,article.isDeleted.eq(false)
                                ,(findExistKeywordInTitle(articleCategoryRequest.getKeyword())
                                        .or(findExistKeyWordInContent(articleCategoryRequest.getKeyword()))
                                        .or(findExistKeyWordInNickName(articleCategoryRequest.getKeyword()))))
                .fetchOne();
        PageImpl<Article> articlesList = new PageImpl<>(content, pageRequest, totalSize);
        Page<ArticleResponse> map = articlesList.map(ArticleResponse::new);
        return map;
    }



    private BooleanBuilder findExistCategory(List<Category> category){
        return category==null ? null : categoryOrCondition(category);
    }
    private BooleanBuilder categoryOrCondition(List<Category> category) {
        BooleanBuilder builder = new BooleanBuilder();
        category.stream().forEach(t->builder.or(article.category.eq(t)));
        return category==null? null : builder;
    }
    private BooleanExpression findExistKeywordInTitle(String keyword) {
        return keyword==null ? null : article.title.contains(keyword);
    }
    private BooleanExpression findExistKeyWordInContent(String keyword){
        return keyword==null ? null :article.content.contains(keyword);
    }
    private BooleanExpression findExistKeyWordInNickName(String keyword){
        return keyword==null ? null:article.member.nickname.contains(keyword);
    }
    private OrderSpecifier[] createOrderSpecifier(String orderBy){
        List<OrderSpecifier> orderSpecifiers =new ArrayList<>();
        if(orderBy.equals("latest")) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC,article.createdAt));
        }
        else if(orderBy.equals("manyComment")) {
            orderSpecifiers.add(new OrderSpecifier(Order.DESC,article.comments));
        }
        else {

        }
        return orderSpecifiers.toArray(new OrderSpecifier[orderSpecifiers.size()]);
    }

}
