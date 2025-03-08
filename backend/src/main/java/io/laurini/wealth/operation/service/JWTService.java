package io.laurini.wealth.operation.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JWTService {

    private final SecretKey secret;

    public JWTService() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
            secret = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generate(String username) {
        return Jwts.builder()
                .claims()
                .add(new HashMap<>())
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .and()
                .signWith(secret)
                .compact();
    }

    public String username(String token) {
        return claim(token, Claims::getSubject);
    }

    public boolean validate(String token, UserDetails details) {
        String username = username(token);
        return (username.equals(details.getUsername()) && !isExpired(token));
    }


    private Boolean isExpired(String token) {
        Date expiration = claim(token, Claims::getExpiration);
        return !Objects.isNull(expiration) && expiration.before(new Date());
    }

    private <T> T claim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }

}
