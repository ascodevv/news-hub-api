package com.ascodev.newshubbackend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtTokenUtils {

    private final String secret1 = "b6eda060fe36d0e2192d30299daf31bb1398bc467ad423e4794144363ed2e11e77ecba89fc7e1303b02e447a1ced4ae8a125194d138beff5df4878290d05b3d5bf02e0be414c721f3a18196cd4281d0342826dc86a56f3c0a4e4b217c34240f6e882a18fc35ecb0c786aed90c2db2584c8db806698986b37ec0b388c206861e72888b5ffedd82db86f13ffad09cb5b59f42cf17cf79b667e6cb737cab6c169d4ff860d933dd409db94405c49036252dbcb32c5a14dc028e12836d234002c0ff69baa6ccdeaf30306c7080fd0a5870afc457268fe4c94bbbc34bb889eaa0c34ed20b79f87cc0870b8cd0129875b8ebe4a85eac4b3bf005ee7f122d3504fe81ad7";

    SecretKey secretKey = Keys.hmacShaKeyFor(secret1.getBytes(StandardCharsets.UTF_8));

    @PostConstruct
    public void init() {
        this.secretKey = Keys.hmacShaKeyFor(secret1.getBytes(StandardCharsets.UTF_8));
    }

    @Value("${jwt.expire}")
    private Duration expire;

    public String generateToken(UserDetails userDetails) {

        Date issueDate = new Date();
        Date expiryDate = new Date(issueDate.getTime() + expire.toMillis());

        return Jwts.builder()
            .claims(Map.of(
              "sub", userDetails.getUsername(),
                "authorities", userDetails.getAuthorities()
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

    public String getEmailFromToken(String token) {
        return getClaimsFromToken(token).get("email", String.class);
    }

    public List<String> getRolesFromToken(String token) {
        return Collections.singletonList(getClaimsFromToken(token).get("roles", String.class));
    }

}
