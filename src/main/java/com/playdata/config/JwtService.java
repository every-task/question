package com.playdata.config;

import com.playdata.exception.TokenTimeOutException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService {
    @Value("${config.jwt.secret}")
    private String secretKey;
    public TokenInfo parseToken(String token) throws JwtException {
        try {
            Claims body = (Claims) Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parse(token)
                    .getBody();

            return TokenInfo.builder()
                    .id(UUID.fromString(body.get("id", String.class)))
                    .email(body.get("email", String.class))
                    .nickname(body.get("nickname", String.class))
                    .profileImageUrl(body.get("profileImageUrl", String.class))
                    .build();
        }
        catch (JwtException e) {
            throw new TokenTimeOutException("Token is TimeOut");
        }
    }
}
