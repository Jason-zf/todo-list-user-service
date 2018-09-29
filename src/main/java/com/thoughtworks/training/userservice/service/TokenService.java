package com.thoughtworks.training.userservice.service;

import com.thoughtworks.training.userservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    public static final String SECRET_KEY = "secret_key";

    public String createToken(User user1) {
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .claim("id", user1.getId())
                .compact();
        return token;
    }

    public Long parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("id", Long.class);
    }
}
