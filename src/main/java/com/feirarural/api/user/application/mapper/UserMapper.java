package com.feirarural.api.user.application.mapper;

import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.dto.UserRequest;
import com.feirarural.api.user.dto.UserResponse;

public class UserMapper {
    public User toDomain(UserRequest request) {
        return new User(null, request.nome(), request.email(), request.senhaCriptografada(), request.tipo());
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(user.getId(), user.getNome(), user.getEmail(), user.getTipo());
    }
}
