package com.mta.portfolio.auth.util;

import com.mta.portfolio.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private static final String TOKEN_TYPE_CLAIM = "tokenType";
    private static final String ACCESS_TOKEN_TYPE = "access";
    private static final String REFRESH_TOKEN_TYPE = "refresh";

    private static final Logger logger = LogManager.getLogger(JwtTokenProvider.class);

    private final JwtConfig jwtConfig;
    private Key key;

    @PostConstruct
    public void init() {
        log.info("JWT secret length={}",
                jwtConfig.getSecret() == null ? 0 : jwtConfig.getSecret().length());
        this.key = Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return generateToken(userDetails.getUsername(), jwtConfig.getExpirationMs(), ACCESS_TOKEN_TYPE);
    }

    public String generateAccessToken(String username) {
        return generateToken(username, jwtConfig.getExpirationMs(), ACCESS_TOKEN_TYPE);
    }

    public String generateRefreshToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return generateToken(userDetails.getUsername(), jwtConfig.getRefreshExpirationMs(), REFRESH_TOKEN_TYPE);
    }

    private String generateToken(String username, long expirationMs, String tokenType) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .setSubject(username)
                .claim(TOKEN_TYPE_CLAIM, tokenType)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        return validateToken(token, ACCESS_TOKEN_TYPE);
    }

    private boolean validateToken(String token, String tokenType) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return tokenType.equals(claims.get(TOKEN_TYPE_CLAIM, String.class));
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean validateRefreshToken(String token) {
        return validateToken(token, REFRESH_TOKEN_TYPE);
    }

    public long getAccessTokenExpirationMs() {
        return jwtConfig.getExpirationMs();
    }

    public long getRefreshTokenExpirationMs() {
        return jwtConfig.getRefreshExpirationMs();
    }
}
