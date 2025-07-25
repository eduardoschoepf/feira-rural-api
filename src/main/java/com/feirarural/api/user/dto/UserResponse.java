package com.feirarural.api.user.dto;

import com.feirarural.api.user.domain.model.TipoUsuario;
import com.feirarural.api.user.domain.model.User;


public record UserResponse(
    Long id,
    String nome,
    String email,
    TipoUsuario tipo,
    String senha
) {
    public static UserResponse from(User user) {
        return new UserResponse(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getTipo(),
            user.getSenha()
        );
    }
}
