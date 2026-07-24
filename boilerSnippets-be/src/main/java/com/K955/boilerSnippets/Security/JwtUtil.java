package com.K955.boilerSnippets.Security;

import com.K955.boilerSnippets.Entity.User;
import com.K955.boilerSnippets.ExceptionHandling.Exceptions.UserNotFoundException;
import com.K955.boilerSnippets.Repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final UserRepository userRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpiration;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(Long githubId) {
        return Jwts.builder()
                .subject(githubId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSecretKey())
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Long extractGithubId(String token) {
        return Long.parseLong(extractClaims(token).getSubject());
    }

    public Boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public Boolean isTokenValid(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCurrentUserGithubId() { //githubId as string
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public User getCurrentUser() {
        Long githubId = Long.valueOf(getCurrentUserGithubId());
        return userRepository.findByGithubId(githubId)
                .orElseThrow(() -> new UserNotFoundException(githubId.toString()));
    }

    public Long getCurrentUserId() {
        return getCurrentUser().getId();
    }

}
