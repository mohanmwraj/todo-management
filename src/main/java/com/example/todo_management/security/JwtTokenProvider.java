package com.example.todo_management.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private String jwtExpirationDate;

    //Generate JWT Token
    public String generateToken(Authentication authentication){
        String userName = authentication.getName();

        Date currDate = new Date();

        Date expireData = new Date(currDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                        .setSubject(userName)
                        .setIssuedAt(new Date())
                        .setExpiration(expireData)
                        .signWith(key())
                        .compact();

        return token;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    // Get username from JWT Token
    public String getUserName(String token){
         Claims claims = Jwts.parser()
                 .setSigningKey(key())
                 .build()
                 .parseEncryptedClaims(token)
                 .getBody();

         String username = claims.getSubject();
         return username;
    }

    //Validate JWT Token
    public boolean validateToken(String token){

        Jwts.parser()
                .setSigningKey(key())
                .build()
                .parse(token);

        return true;
    }
}
