package com.feirarural.api.config.security;

import com.feirarural.api.auth.adapter.out.persistence.AuthJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final AuthJpaRepository authRepository;

    @Override
    public void logout(HttpServletRequest request, 
                      HttpServletResponse response, 
                      Authentication authentication) {
        
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        
        final String jwt = authHeader.substring(7);
        authRepository.findByToken(jwt)
            .ifPresent(authRepository::delete);
    }
}