package com.loginlogout.loginlogout.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secreteKey;

    private final long expirationHours;

    private final long refreshExpirationHours;

    public JwtTokenProvider(@Value("${jwt.secret}") String secreteKey, @Value("${jwt.expiration}") long expirationHours, @Value("${jwt.refresh-expiration}") long refreshExpirationHours) {
        this.secreteKey = secreteKey;
        this.expirationHours = expirationHours;
        this.refreshExpirationHours = refreshExpirationHours;
    }

    public String createToken() {

        Claims claims = Jwts.claims();

        long now = new Date().getTime();

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, secreteKey)
                .setClaims(claims)
                .setExpiration(new Date(now + expirationHours))
                .compact();

    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secreteKey).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedJwtException e) {
            throw new RuntimeException(e);
        } catch (MalformedJwtException e) {
            throw new RuntimeException(e);
        } catch (SignatureException e) {
            throw new RuntimeException(e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
