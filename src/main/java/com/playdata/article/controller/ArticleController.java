package com.playdata.article.controller;

import com.playdata.article.service.ArticleService;
import com.playdata.config.TokenInfo;
import com.playdata.domain.article.entity.Category;
import com.playdata.exception.NotCorrectTokenIdException;
import com.playdata.domain.article.entity.Article;
import com.playdata.domain.article.request.ArticleCategoryRequest;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.article.response.ArticleDetailResponse;
import com.playdata.domain.article.response.ArticleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//

@RestController
//추후 수정 mapping 주소
@RequestMapping("/api/v1/question/article")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")

public class ArticleController {
    private final ArticleService articleService;
    //질문 등록
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@AuthenticationPrincipal TokenInfo tokenInfo, @RequestBody ArticleRequest articleRequest)
    {
        articleService.insert(articleRequest, tokenInfo.getId());
    }

    //질문 조회
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ArticleResponse> getAll(@RequestParam(value ="page",required = false,defaultValue = "0")int page,
                                        @RequestParam(value="size", required = false, defaultValue = "10")int size,
                                        @RequestParam(value="category", required = false)  List<String> category)
    {
        if(category==null) {
            List<String> category2 = new ArrayList<>();
            ArticleCategoryRequest articleCategoryRequest =new ArticleCategoryRequest(category2.stream().map(Category::valueOf).toList());
            return articleService.getAll(PageRequest.of(page,size),articleCategoryRequest);
        }
        ArticleCategoryRequest articleCategoryRequest =new ArticleCategoryRequest(category.stream().map(Category::valueOf).toList());
        return articleService.getAll(PageRequest.of(page,size),articleCategoryRequest);
    }

    //질문 삭제
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@AuthenticationPrincipal TokenInfo tokenInfo,@PathVariable Long id) throws NotCorrectTokenIdException
    {
        articleService.deleteById(tokenInfo,id);
    }
    //질문 수정
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Article updateArticle(@AuthenticationPrincipal TokenInfo tokenInfo,
                                 @PathVariable Long id ,
                                 @RequestBody ArticleRequest articleRequest) throws NotCorrectTokenIdException {
        return articleService.updateArticle(tokenInfo,id,articleRequest);
    }

//    질문 상세 조회
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDetailResponse getById(@PathVariable("id")Long id) {
        return articleService.getById(id);
    }
}
