package com.playdata.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class JwtService {
    private final String secretKey = "anfoawhfafawkefhbwkjlfeopwehfolawefh";

    public TokenInfo parseToken(String token){
        Claims body = (Claims) Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parse(token)
                //여기서 발생하니까 잡아주면 됨
                .getBody();

        return TokenInfo.builder()
                .id(UUID.fromString(body.get("id", String.class)))
                .email(body.get("email", String.class))
                .nickname(body.get("nickname", String.class))
                .profileImageUrl(body.get("profileImageUrl", String.class))
                .build();
    }
}
