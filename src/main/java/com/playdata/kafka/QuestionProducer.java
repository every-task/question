package com.playdata.kafka;

import com.playdata.domain.article.kafka.ArticleKafka;
import com.playdata.domain.member.kafka.Action;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class QuestionProducer {
    private final KafkaTemplate<String, KafkaData<ArticleKafka>> kafkaTemplate;
    @Async
    public void sendArticleCreate(ArticleKafka articleKafka) {
        CompletableFuture<SendResult<String, KafkaData<ArticleKafka>>> resultCompletableFuture =
                kafkaTemplate.send(TopicConfig.QUESTION,
                        KafkaData.create(articleKafka, Action.valueOf("CREATE")));
                if(resultCompletableFuture.isCompletedExceptionally()){
                    throw new RuntimeException("send failure");
                }
                resultCompletableFuture.thenAccept(result ->
                        System.out.println("send After "
                                + articleKafka + " "
                                + result.getRecordMetadata()
                                .offset()));
        log.info("send={}",articleKafka);
    }
    @Async
    public void sendArticleDelete(ArticleKafka articleKafka) {
        CompletableFuture<SendResult<String, KafkaData<ArticleKafka>>> resultCompletableFuture =
                kafkaTemplate.send(TopicConfig.QUESTION,
                        KafkaData.create(articleKafka, Action.valueOf("DELETE")));
        if(resultCompletableFuture.isCompletedExceptionally()){
            throw new RuntimeException("send failure");
        }
        resultCompletableFuture.thenAccept(result ->
                System.out.println("send After "
                        + articleKafka + " "
                        + result.getRecordMetadata()
                        .offset()));
        log.info("send={}",articleKafka);
    }
    @Async
    public void sendArticleUpdate(ArticleKafka articleKafka) {
        CompletableFuture<SendResult<String, KafkaData<ArticleKafka>>> resultCompletableFuture =
                kafkaTemplate.send(TopicConfig.QUESTION,
                        KafkaData.create(articleKafka, Action.valueOf("UPDATE")));
        if(resultCompletableFuture.isCompletedExceptionally()){
            throw new RuntimeException("send failure");
        }
        resultCompletableFuture.thenAccept(result ->
                System.out.println("send After "
                        + articleKafka + " "
                        + result.getRecordMetadata()
                        .offset()));
        log.info("send={}",articleKafka);
    }
}
