package com.playdata.exception;


import com.playdata.exception.response.ExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.WeakKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionAdvice {

    @ExceptionHandler(NoArticleByIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String resourceNotFoundExceptionHandler(NoArticleByIdException e) {
        return e.getMessage();
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponse weakKeyExceptionHandler(ExpiredJwtException e){
        return new ExceptionResponse(
                "토큰의 사용기간이 지났습니다.", e.getCause());
    }




}
