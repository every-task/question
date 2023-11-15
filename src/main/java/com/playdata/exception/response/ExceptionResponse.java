package com.playdata.exception.response;

import lombok.*;

import static com.playdata.exception.status.Status.FAILED;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String status;
    private String message;
    public static ExceptionResponse responseBuilder(String message) {
        return ExceptionResponse
                .builder()
                .status(FAILED.toString())
                .message(message)
                .build();
    }

}
