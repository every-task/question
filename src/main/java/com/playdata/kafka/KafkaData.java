package com.playdata.kafka;

import com.playdata.domain.member.kafka.Action;
import lombok.Builder;

@Builder
public record KafkaData<T>(Action action,T data) {
    public static <T>KafkaData<T> create(T data, Action action) {
      return KafkaData
              .<T>builder()
              .data(data)
              .action(action)
              .build();
    }

}
