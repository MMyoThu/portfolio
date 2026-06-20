package com.mta.portfolio.auth.service;

import com.mta.portfolio.auth.dto.LoginRequest;
import com.mta.portfolio.auth.dto.LoginResponse;
import com.mta.portfolio.auth.dto.RefreshTokenRequest;
import com.mta.portfolio.auth.dto.SignupRequest;

public interface AuthService {


    LoginResponse login(LoginRequest loginRequest);

    LoginResponse signup(SignupRequest signupRequest);

    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
