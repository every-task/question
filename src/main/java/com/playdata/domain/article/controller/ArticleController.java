package com.playdata.domain.article.controller;

import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.response.ArticleResponse;
import com.playdata.domain.article.service.ArticleService;
import com.playdata.domain.article.request.ArticleRequest;
import lombok.RequiredArgsConstructor;
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



}
