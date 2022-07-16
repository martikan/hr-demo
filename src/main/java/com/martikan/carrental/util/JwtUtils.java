package com.martikan.carrental.util;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.martikan.carrental.security.UserDetails;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

/**
 * Utils class to take care of JWT tokens.
 */
@Slf4j
@Component
public class JwtUtils {

    @Value("${token.expiration}")
    private String expirationTime;

    @Value("${token.secret}")
    private String secret;

    public String generateToken(final UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(Objects.requireNonNull(expirationTime))))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * Parse JWT token.
     * @param token JWT token
     * @return true if it's valid
     */
    public String parseJwt(final String token) {

        String username = null;

        try {

            final var subject = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            if (subject != null && !subject.isBlank()) {
                username = subject;
            }

        } catch (Exception e) {
            log.warn("JWT Token validation error", e);
        }

        return username;
    }

}