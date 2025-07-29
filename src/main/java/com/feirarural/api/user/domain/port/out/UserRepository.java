package com.feirarural.api.user.domain.port.out;

import com.feirarural.api.user.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> listarTodos();
    User salvar(User user);
    Optional<User> buscarPorId(Long id);
    void excluir(User user);
    Optional<User> buscarPorEmail(String email);
    boolean existePorEmail(String email);
}
