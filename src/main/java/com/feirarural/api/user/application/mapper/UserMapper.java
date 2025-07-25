package com.feirarural.api.user.application.mapper;

import org.springframework.stereotype.Component;

import com.feirarural.api.user.domain.model.TipoUsuario;
import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.dto.UserRequest;
import com.feirarural.api.user.dto.UserResponse;

@Component
public class UserMapper {
    public User toDomain(UserRequest request) {
        try {
            TipoUsuario tipoUsuario = TipoUsuario.valueOf(request.tipo().toUpperCase());
            return new User(null, request.nome(), request.email(), request.senha(), tipoUsuario);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Tipo de usuário inválido: " + request.tipo());
        }
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getNome(), user.getEmail(), user.getTipo(), user.getSenha());
    }
}
