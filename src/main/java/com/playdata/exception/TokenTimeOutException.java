package com.playdata.exception;

import io.jsonwebtoken.JwtException;

public class TokenTimeOutException extends JwtException {
    public TokenTimeOutException(String message) {
        super(message);
    }
}
