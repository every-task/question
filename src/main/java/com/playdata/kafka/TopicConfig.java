package com.playdata.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    public final static String QUESTION = "question";
    public final static String MEMBER = "member";

    @Bean
    public NewTopic question() {
        return TopicBuilder
                .name(QUESTION)
                .partitions(1)
                .replicas(1)
                .build();
    }

}
