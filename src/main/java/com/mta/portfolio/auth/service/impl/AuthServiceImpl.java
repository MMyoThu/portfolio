package com.mta.portfolio.auth.service.impl;

import com.mta.portfolio.auth.dto.LoginRequest;
import com.mta.portfolio.auth.dto.LoginResponse;
import com.mta.portfolio.auth.dto.RefreshTokenRequest;
import com.mta.portfolio.auth.dto.SignupRequest;
import com.mta.portfolio.auth.entity.AdminUser;
import com.mta.portfolio.auth.repository.AdminUserRepository;
import com.mta.portfolio.auth.service.AuthService;
import com.mta.portfolio.auth.util.JwtTokenProvider;
import com.mta.portfolio.common.constant.AppConstants;
import com.mta.portfolio.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AdminUserRepository adminUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
        return buildTokenResponse(authentication);
    }

    @Override
    @Transactional
    public LoginResponse signup(SignupRequest signupRequest) {
        if (adminUserRepository.existsByUsername(signupRequest.getUsername())) {
            throw new ApiException(HttpStatus.CONFLICT, "Username already exists");
        }

        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(signupRequest.getUsername());
        adminUser.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        adminUser.setRole(AppConstants.ROLE_ADMIN);
        adminUserRepository.save(adminUser);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signupRequest.getUsername(), signupRequest.getPassword())
        );
        return buildTokenResponse(authentication);
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String refreshToken = refreshTokenRequest.getRefreshToken();
        if (!jwtTokenProvider.validateRefreshToken(refreshToken)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        String username = jwtTokenProvider.getUsernameFromToken(refreshToken);
        if (!adminUserRepository.existsByUsername(username)) {
            throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid refresh token");
        }

        return new LoginResponse(
                jwtTokenProvider.generateAccessToken(username),
                refreshToken,
                AppConstants.TOKEN_TYPE_BEARER,
                jwtTokenProvider.getAccessTokenExpirationMs(),
                jwtTokenProvider.getRefreshTokenExpirationMs()
        );
    }

    private LoginResponse buildTokenResponse(Authentication authentication) {
        return new LoginResponse(
                jwtTokenProvider.generateToken(authentication),
                jwtTokenProvider.generateRefreshToken(authentication),
                AppConstants.TOKEN_TYPE_BEARER,
                jwtTokenProvider.getAccessTokenExpirationMs(),
                jwtTokenProvider.getRefreshTokenExpirationMs()
        );
    }
}
