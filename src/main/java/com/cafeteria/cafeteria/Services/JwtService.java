package com.cafeteria.cafeteria.Services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.security.Key;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "z8KZqkPpY8vH5fA1rXzJ0l7s4MZ+U2EJx0Q0cWm5QdM=";

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(SECRET_KEY)
        );
    }

    public String generateToken(String username) {
        try {
            return Jwts.builder()
                    .setSubject(username)
                    .setIssuedAt(new Date())
                    .setExpiration(
                            new Date(System.currentTimeMillis() + 1000 * 60 * 60)
                    )
                    .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                    .compact();

        } catch (JwtException ex) {
            throw new RuntimeException("Erro ao gerar JWT", ex);
        }
    }
}
