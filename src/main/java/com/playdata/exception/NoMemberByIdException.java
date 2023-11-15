package com.playdata.exception;

public class NoMemberByIdException extends RuntimeException{
    public NoMemberByIdException(String message)
    {
        super(message);
    }
}
