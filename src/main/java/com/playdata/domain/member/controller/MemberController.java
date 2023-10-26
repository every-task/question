package com.playdata.domain.member.controller;

import com.playdata.domain.member.kafka.MemberKafkaData;
import com.playdata.domain.member.repository.MemberRepository;
import com.playdata.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/question/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @PostMapping
    public void save(MemberKafkaData memberKafkaData)
    {
        memberService.save(memberKafkaData);
    }
}
