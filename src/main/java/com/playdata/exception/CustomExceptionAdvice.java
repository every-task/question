package com.playdata.exception;


import com.playdata.exception.response.ExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.WeakKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionAdvice {

    @ExceptionHandler(NoArticleByIdException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String resourceNotFoundExceptionHandler(NoArticleByIdException e) {
        return e.getMessage();
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void weakKeyExceptionHandler(ExpiredJwtException e){
        log.error("error 401", e);
    }




}
