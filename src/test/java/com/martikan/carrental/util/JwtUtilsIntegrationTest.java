package com.martikan.carrental.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.martikan.carrental.CarrentalApplicationTests;
import com.martikan.carrental.security.UserDetails;

/**
 * Integration test needed because we have to load the context for
 * `${token.expiration}` and `${token.secret}`
 */
@ExtendWith(SpringExtension.class)
public class JwtUtilsIntegrationTest extends CarrentalApplicationTests {

    @Autowired
    private JwtUtils jwtUtils;

    private final UserDetails userDetails = UserDetails.builder()
            .id(1L)
            .username("asd@info.com")
            .build();

    @Test
    void parseJwtTest_Valid() {

        // Arrange
        final var username = userDetails.getUsername();

        final var jwtToken = jwtUtils.generateToken(userDetails);

        // Act
        final var parsedUsername = jwtUtils.parseJwt(jwtToken);

        // Assert
        assertEquals(username, parsedUsername);
    }

}