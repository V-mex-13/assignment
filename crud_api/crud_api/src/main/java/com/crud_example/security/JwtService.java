package com.crud_example.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "myjwtsecretkeymyjwtsecretkeymyjwtsecretkey123456";

    private static final long EXPIRATION_TIME = 15 * 60 * 1000; // 15 minutes

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // ===============================
    // GENERATE TOKEN
    // ===============================
    public String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

//    getusername from token


    public  String getUsername(String token){


        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();


    }


//    to validate token

     public boolean validatetoken(String token){


        try {

            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);

            return true;

        } catch (Exception e) {

            return false;
        }


     }



}
