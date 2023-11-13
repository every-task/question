package com.playdata.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    public final static String QUESTION = "question"; //내가 발행한 토픽
    public final static String MEMBER = "member"; //내가 구독한 토픽
    public final static String memberDLT = "member.DLT";

    @Bean
    public NewTopic memberDLT(){
        return new NewTopic(memberDLT,1,(short) 1);

    }
    @Bean
    public NewTopic question() {
        return TopicBuilder
                .name(QUESTION)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
