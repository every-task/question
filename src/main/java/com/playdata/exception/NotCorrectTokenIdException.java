package com.playdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NotCorrectTokenIdException extends Exception{
    public NotCorrectTokenIdException(String message) {
        super(message);
    }
}
