package com.playdata.article.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.playdata.config.TokenInfo;
import com.playdata.domain.article.entity.Category;
import com.playdata.domain.article.repository.ArticleRepository;
import com.playdata.domain.article.request.ArticleRequest;
import com.playdata.domain.member.entity.Member;
import com.playdata.domain.member.kafka.Action;
import com.playdata.domain.member.kafka.MemberKafka;
import com.playdata.domain.member.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.authentication;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class ArticleControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Transactional
    @Nested
    //class로 하나로 묶어줌
    class 작성글_등록 {
        @Test
        void 작성글_등록_성공() throws Exception {
            ArticleRequest articleRequest = new ArticleRequest(content,title,category);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/question/article")
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJuaWNrbmFtZSI6Iuq4sOumsCIsImlkIjoiZjEwYzdhMTMtZWNhOS00OTExLWE4M2YtMWUwNmQ4N2ZkNDRlIiwicHJvZmlsZUltYWdlVXJsIjoiaHR0cHM6Ly9jZG4ucGl4YWJheS5jb20vcGhvdG8vMjAyMy8wOS8yMS8xOC8xNy9hdXRvbW9iaWxlLTgyNjczNjlfMTI4MC5qcGciLCJlbWFpbCI6IjgiLCJleHAiOjE3MDA0NTg3Njd9.6bDJWKkKlcxPU7Fy82_9JrXRMbPwpxbluKIWsEfWonM")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(articleRequest)))
                    .andExpect(status().isCreated());
        }
        @Test
        void 로그인_안함() throws Exception {
            ArticleRequest articleRequest = new ArticleRequest(content,title,category);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/question/article")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(articleRequest)))
                    .andExpect(status().isUnauthorized());
        }
        @Test
        void 권한_실패() throws Exception {
            ArticleRequest articleRequest = new ArticleRequest(content,title,category);
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/question/article")
                            .header(HttpHeaders.AUTHORIZATION, "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJuaWNrbmFtZSI6Iuq4sOumsCIsImlkIjoiZjEwYzdhMTMtZWNhOS00OTExLWE4M2YtMWUwNmQ4N2ZkNDRlIiwicHJvZmlsZUltYWdlVXJsIjoiaHR0cHM6Ly9jZG4ucGl4YWJheS5jb20vcGhvdG8vMjAyMy8wOS8yMS8xOC8xNy9hdXRvbW9iaWxlLTgyNjczNjlfMTI4MC5qcGciLCJlbWFpbCI6IjgiLCJleHAiOjE3MDA0NTg3Njd9.6bDJWKkKlcxPU7Fy82_9JrXRMbPwpxbluKIWsEfWonM")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(articleRequest)))
                    .andExpect(status().isUnauthorized());
        }
        @Test
        void 실패() {

        }
    }
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    EntityManager entityManager;
    UUID id = UUID.fromString("d5941a88-f022-4f1c-a29f-1e2e7534035c");
    Member member;
    String email= "7";
    String nickname = "기린";
    String profileImageUrl = "https://cdn.pixabay.com/photo/2023/09/21/18/17/automobile-8267369_1280.jpg";

    String title = "영어를 잘하고 싶어요 알려주세요.";
    String content ="배우고 싶어요 알려주세요.";
    Category category = Category.valueOf("LANGUAGE");
//    @BeforeEach
//    void init (){
//        Member member = new Member(id,nickname,profileImageUrl);
//        this.member = memberRepository.save(member);
//        entityManager.flush();
//        entityManager.clear();
//    }
    @AfterEach
    void clean() {
//        memberRepository.deleteAll();
        articleRepository.deleteAll();

    }






}