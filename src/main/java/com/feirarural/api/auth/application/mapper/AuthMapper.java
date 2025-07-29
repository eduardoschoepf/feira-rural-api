package com.feirarural.api.auth.application.mapper;

import org.springframework.stereotype.Component;

import com.feirarural.api.auth.adapter.out.persistence.AuthEntity;
import com.feirarural.api.auth.domain.model.AuthInfo;
import com.feirarural.api.user.adapter.out.persistence.UserEntity;
import com.feirarural.api.user.domain.model.User;

@Component
public class AuthMapper {

    public AuthInfo toDomain(AuthEntity entity) {
        return new AuthInfo(
            entity.getId(),
            entity.getUser().getId(), // Apenas o ID do usuário
            entity.getRefreshToken(),
            entity.getExpiresAt(),
            entity.isActive()
        );
    }

    public AuthEntity toEntity(AuthInfo domain, User user) {
        // Converte User para UserEntity dentro do mapper
        return new AuthEntity(
            toUserEntity(user),
            domain.getRefreshToken(),
            domain.getExpiresAt(),
            domain.isActive()
        );
    }

    private UserEntity toUserEntity(User user) {
        return new UserEntity(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getSenha(),
            user.getRole()
        );
    }
}