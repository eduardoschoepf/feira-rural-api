package com.feirarural.api.user.domain.port.in;

import java.util.Optional;

import com.feirarural.api.user.domain.model.User;

public interface UserUseCase {
    
    User salvar(User user);
    Optional<User> buscarPorEmail(String email);
}