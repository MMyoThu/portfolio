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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @CrossOrigin(origins = {"http://localhost:5173", "https://myothuaung.vercel.app"})
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Success", authService.login(loginRequest)));
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = {"http://localhost:5173", "https://myothuaung.vercel.app"})
    public ResponseEntity<ApiResponse<LoginResponse>> signup(@Valid @RequestBody SignupRequest signupRequest) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Signup successful", authService.signup(signupRequest)));
    }

    @PostMapping("/refresh-token")
    @CrossOrigin(origins = {"http://localhost:5173", "https://myothuaung.vercel.app"})
    public ResponseEntity<ApiResponse<LoginResponse>> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(new ApiResponse<>(true, "Token refreshed", authService.refreshToken(refreshTokenRequest)));
    }
}
