package com.martikan.carrental.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martikan.carrental.dto.auth.SignUpDTO;
import com.martikan.carrental.dto.user.UserDTO;
import com.martikan.carrental.service.UserService;

import lombok.RequiredArgsConstructor;

/**
 * Authentication controller.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;

    /**
     * Sign-in end-point is implemented in {@link WebSecurity}
     */
    
    /**
     * Endpoint for Sign-up action.
     * @param signUpRequest {@link SignUpDTO}
     * @return {@link UserDTO}
     */
    @PostMapping("/signUp")
    public ResponseEntity<UserDTO> signUp(@Valid @RequestBody final SignUpDTO signUpRequest) {
        return ResponseEntity.ok(userService.signUp(signUpRequest));
    }

}