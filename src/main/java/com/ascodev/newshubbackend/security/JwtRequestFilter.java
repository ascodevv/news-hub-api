package com.ascodev.newshubbackend.security;

import com.ascodev.newshubbackend.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenUtils jwtTokenUtils;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwtToken = authHeader.substring(7);
            try {
                username = jwtTokenUtils.getUsernameFromToken(jwtToken);
            }
            catch (ExpiredJwtException e) {
                System.out.println("JWT token expired");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            catch (SignatureException e) {
                System.out.println("JWT token signature exception");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
           UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                   username,
                   null,
                   jwtTokenUtils.getAuthorities(jwtToken).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
           );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
