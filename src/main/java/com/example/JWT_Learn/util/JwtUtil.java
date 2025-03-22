package com.example.JWT_Learn.util;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
private final SecretKey secretKey = Keys.hmacShaKeyFor("YourSuperSecretKeyHere".getBytes(StandardCharsets.UTF_8));

    public String generateToken(Authentication authentication) {
        return createToken(authentication.getName(), authentication.getAuthorities(), 1000 * 60 * 60); // 1 hour expiry
    }

    public String generateRefreshToken(Authentication authentication) {
        return createToken(authentication.getName(), authentication.getAuthorities(), 1000 * 60 * 60 * 24 * 7); // 7 days expiry
    }
    
    private String createToken(String username, Collection<? extends GrantedAuthority> authorities, long expirationTime) {
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(secretKey)
                .compact();
    }
    
    public Claims extractClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
}
