package com.feirarural.api.user.domain.port.in;

import java.util.List;
import java.util.Optional;

import com.feirarural.api.user.domain.model.User;

public interface UserUseCase {
    List<User> listarTodos();
    User salvar(User user);
    User buscarPorId(Long id);
    User atualizar(Long id, User request);
    void excluir(Long id);
    Optional<User> autenticar(String email, String senha);
    Optional<User> buscarPorEmail(String email);
}