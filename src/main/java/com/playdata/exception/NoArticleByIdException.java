package com.playdata.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class NoArticleByIdException extends RuntimeException{
    public NoArticleByIdException(String message)
    {
        super(message);
    }


}
