package com.playdata.domain.article.controller;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.article.service.ArticleService;
import com.playdata.domain.article.request.ArticleRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//추후 수정 mapping 주소
@RequestMapping("/api/v1/question/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;
    //질문 등록
    @PostMapping()
    public void insert(@RequestBody ArticleRequest articleRequest)
    {
        articleService.insert(articleRequest);
    }
    @GetMapping
    //질문 조회
    public List<ArticleResponse> getAll()
    {
        return articleService.getAll();
    }
    //질문 삭제
    @DeleteMapping("{id}")
    public void deleteArticle(@PathVariable Long id)
    {
        articleService.deleteById(id);
    }
    //질문 수정
    @PutMapping("{id}")
    public Article updateArticle(@PathVariable Long id , @RequestBody ArticleRequest articleRequest)
    {
        return articleService.updateArticle(id,articleRequest);

    }
    //질문 카테고리 조회
    @GetMapping("/category")
    public List<ArticleResponse> getByCategory(@RequestBody ArticleCategoryRequest articleCategoryRequest)
    {
        return articleService.getByCategory(articleCategoryRequest);
    }
    //질문 상세 조회
    @GetMapping("/{id}")
    public Article getById(@PathVariable("id")Long id)
    {
        return articleService.getById(id);
    }


}
