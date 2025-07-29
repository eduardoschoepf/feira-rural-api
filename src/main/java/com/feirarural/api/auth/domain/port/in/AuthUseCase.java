package com.feirarural.api.auth.domain.port.in;

import com.feirarural.api.auth.dto.AuthRequest;
import com.feirarural.api.auth.dto.AuthResponse;
import com.feirarural.api.auth.dto.RegisterRequest;

public interface AuthUseCase {
    AuthResponse register(RegisterRequest request);
    AuthResponse authenticate(AuthRequest request);
    void logout(String refreshToken);
}