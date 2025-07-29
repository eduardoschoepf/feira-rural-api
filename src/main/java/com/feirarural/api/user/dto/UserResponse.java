package com.feirarural.api.user.dto;

import com.feirarural.api.user.domain.model.User;


public record UserResponse(
    Long id,
    String nome,
    String email,
    String role,
    String senha
) {
    public static UserResponse from(User user) {
        return new UserResponse(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getSenha(),
            user.getRole().name() // Usamos o nome do enum (ADMIN, USUARIO)
        );
    }
}
