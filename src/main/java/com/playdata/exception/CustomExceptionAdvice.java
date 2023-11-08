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
        log.error("No Ariticle Exception",e);
        return e.getMessage();
    }
    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String weakKeyExceptionHandler(ExpiredJwtException e){
        log.error("Weakey Exception", e);
        return e.getMessage();
    }




}
