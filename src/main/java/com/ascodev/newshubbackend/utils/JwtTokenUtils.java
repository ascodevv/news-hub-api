package com.ascodev.newshubbackend.utils;

import com.ascodev.newshubbackend.security.MyUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.*;

@Getter
@Component
@RequiredArgsConstructor
public class JwtTokenUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expire}")
    private Duration expire;

    private SecretKey secretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        secretKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(MyUserDetails userDetails) {

        List<String> authorities = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .toList();

        Date issueDate = new Date();
        Date expiryDate = new Date(issueDate.getTime() + expire.toMillis());

        return Jwts.builder()
            .claims(Map.of(
                "id", userDetails.getUserId(),
                "sub", userDetails.getUsername(),
                "authorities", authorities,
                "status", userDetails.getUserStatus()

        ))
        .issuedAt(issueDate)
        .expiration(expiryDate)
        .signWith(secretKey)
        .compact();
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
            .verifyWith(secretKey)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).getSubject();
    }

    public List<String> getAuthorities (String token) {
        Claims claims = getClaimsFromToken(token);
        Object authoritiesObj = claims.get("authorities");

        if (authoritiesObj instanceof List<?>) {
            return ((List<?>) authoritiesObj).stream().map(Object::toString).toList();
        }
        return Collections.emptyList();
    }

}
