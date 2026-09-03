package com.mta.portfolio.auth.controller;

import com.mta.portfolio.auth.dto.LoginRequest;
import com.mta.portfolio.auth.dto.LoginResponse;
import com.mta.portfolio.auth.dto.RefreshTokenRequest;
import com.mta.portfolio.auth.dto.SignupRequest;
import com.mta.portfolio.auth.service.AuthService;
import com.mta.portfolio.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(loginRequest)));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<LoginResponse>> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(ApiResponse.success("Signup successful", authService.signup(signupRequest)));
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(ApiResponse.success("Token refreshed", authService.refreshToken(refreshTokenRequest)));
    }
}
