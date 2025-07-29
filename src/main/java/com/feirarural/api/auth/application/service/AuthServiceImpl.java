package com.feirarural.api.auth.application.service;

import com.feirarural.api.auth.domain.port.in.AuthUseCase;
import com.feirarural.api.auth.dto.AuthRequest;
import com.feirarural.api.auth.dto.AuthResponse;
import com.feirarural.api.auth.dto.RegisterRequest;
import com.feirarural.api.config.security.JwtService;
import com.feirarural.api.shared.domain.model.Role;
import com.feirarural.api.user.adapter.in.security.CustomUserDetailsService;
import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.out.UserRepository;
import com.feirarural.api.auth.adapter.out.persistence.AuthJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthUseCase {

    private final UserRepository userRepository;
    private final AuthJpaRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // 1. Cria o novo usuário
        User user = new User(
            null, // ID será gerado pelo banco
            request.nome(),
            request.email(),
            passwordEncoder.encode(request.senha()),
            Role.USUARIO
        );
        
        // 2. Salva o usuário
        User savedUser = userRepository.salvar(user);
        
        // 3. Cria os tokens
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(savedUser.getEmail());
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        
        // 4. Retorna a resposta
        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse authenticate(AuthRequest request) {
        // 1. Busca diretamente o usuário (a autenticação já foi feita pelo filtro)
        User user = userRepository.buscarPorEmail(request.email())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        // 2. Gera os tokens
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(user.getEmail());
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = jwtService.generateRefreshToken(userDetails);
        
        // 3. Retorna a resposta
        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public void logout(String refreshToken) {
        authRepository.findByRefreshToken(refreshToken)
                .ifPresent(auth -> authRepository.deleteById(auth.getId()));
    }
}