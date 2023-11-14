package com.playdata.exception.response;

import lombok.*;

import static com.playdata.exception.status.Status.FAILED;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String status;
    private String code;
    private String message;
    public static ExceptionResponse responseBuilder(String code, String message) {
        return ExceptionResponse
                .builder()
                .status(FAILED.toString())
                .code(code)
                .message(message)
                .build();
    }

}
