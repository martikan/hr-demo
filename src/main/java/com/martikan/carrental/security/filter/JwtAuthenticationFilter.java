package com.martikan.carrental.security.filter;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.martikan.carrental.exception.ApiException;
import com.martikan.carrental.service.UserService;
import com.martikan.carrental.util.JwtUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT authentication filter.
 */
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (StringUtils.hasText(request.getHeader(HttpHeaders.AUTHORIZATION))) {

            try {

                final var authHeader = Objects.requireNonNull(request.getHeader(HttpHeaders.AUTHORIZATION));

                final var parts = authHeader.split(" ");

                if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                    throw new Exception("Incorrect authorization");
                }

                // If the token cannot be parsed then throw an error.
                var username = jwtUtils.parseJwt(parts[1]);
                if (username == null) {
                    throw new ApiException("Invalid JWT token");
                }

                final var userDetails = userService.loadUserByUsername(username);

                final var authentication = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch(Exception e){
                throw new ApiException(e.getMessage(), e);
            }
        }

        filterChain.doFilter(request, response);

    }

}