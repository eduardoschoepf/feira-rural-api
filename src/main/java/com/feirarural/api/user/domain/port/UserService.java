package com.feirarural.api.user.domain.port;

import java.util.List;

import com.feirarural.api.user.dto.UserRequest;
import com.feirarural.api.user.dto.UserResponse;

public interface UserService {
    UserResponse cadastrar(UserRequest user);
    UserResponse atualizar(Long id, UserRequest request);
    boolean excluir(Long id);
    UserResponse buscarPorId(Long id);
    UserResponse buscarPorEmail(String email);
    List<UserResponse> listarTodos();
}