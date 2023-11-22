package com.example.api_gateway.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "secretkeyexamplesecretkeyexamplesecretkeyexamplesecretkeyexample";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public void isTokenValid(String token) {
        if (isTokenExpired(token))
            throw new RuntimeException("Token is expired");
    }

    public boolean isTokenExpired(String token) {
        return extractExpression(token).before(new Date());
    }

    private Date extractExpression(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
