package com.feirarural.api.user.domain.port;

import com.feirarural.api.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> listarTodos();
    User cadastrar(User user);
    Optional<User> buscarPorId(Long id);
    Optional<User> buscarPorEmail(String email);
    void excluir(User user);
}
