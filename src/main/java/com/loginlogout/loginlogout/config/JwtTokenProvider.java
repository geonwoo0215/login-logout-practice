package com.loginlogout.loginlogout.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String secreteKey;

    private final long expirationHours;

    public JwtTokenProvider(@Value("${") String secreteKey, long expirationHours) {
        this.secreteKey = secreteKey;
        this.expirationHours = expirationHours;
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
