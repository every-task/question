package com.playdata.exception;

import jakarta.servlet.ServletException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NoTokenException extends ServletException {
    public NoTokenException(String message) {
        super(message);
    }
}
