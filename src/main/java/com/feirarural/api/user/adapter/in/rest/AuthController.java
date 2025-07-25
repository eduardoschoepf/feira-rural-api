package com.feirarural.api.user.adapter.in.rest;

import com.feirarural.api.user.adapter.in.security.JwtUtil;
import com.feirarural.api.user.dto.LoginRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
   
    private final AuthenticationManager authManager;
    
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        try {
            // Autentica usuário/senha
            Authentication auth = new UsernamePasswordAuthenticationToken(
                request.email(), 
                request.senha()
            );
            Authentication authenticatedAuth = authManager.authenticate(auth);

            // Gera token JWT
            String token = jwtUtil.generateToken(authenticatedAuth);
            
            return ResponseEntity.ok(token); // Retorna apenas o token
            
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
        }
    }
}
