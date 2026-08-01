package com.dev.personalFinanceTracker.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "Giridharan_Mohan is the developer";
    private final long EXPIRATION = 1000 * 60 * 60 * 24;
    private final Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));


    public String generateToken(String email){
        return Jwts.builder()
                .subject(email)
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .issuedAt(new Date(System.currentTimeMillis()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();

    }

    public String extractEmail(String token){
        return Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseSignedClaims(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token){
        try {
            extractEmail(token);
            return true;
        }catch (JwtException e){
            return false;
        }
    }
}
