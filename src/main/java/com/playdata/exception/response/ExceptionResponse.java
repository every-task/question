package com.playdata.exception.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private Throwable cause;

}
