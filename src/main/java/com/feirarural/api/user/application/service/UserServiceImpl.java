package com.feirarural.api.user.application.service;

import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.UserRepository;
import com.feirarural.api.user.domain.port.UserService;
import com.feirarural.api.user.dto.UserRequest;
import com.feirarural.api.user.dto.UserResponse;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserResponse> listarTodos() {
        return userRepository.listarTodos().stream()
                .map(UserResponse::from)
                .toList();
    }

    @Override
    public UserResponse cadastrar(UserRequest user) {
        User newUser = new User(null, user.nome(), user.email(), user.senhaCriptografada(), user.tipo());
        User savedUser = userRepository.cadastrar(newUser);
        return UserResponse.from(savedUser);
    }

    @Override
    public UserResponse atualizar(Long id, UserRequest request) {
        User user = userRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));

        user.setNome(request.nome());
        user.setEmail(request.email());
        user.setSenhaCriptografada(request.senhaCriptografada());
        user.setTipo(request.tipo());

        User updatedUser = userRepository.cadastrar(user);
        return UserResponse.from(updatedUser);
    }

    @Override
    public UserResponse buscarPorId(Long id) {  
        User user = userRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        return UserResponse.from(user);
    }

    @Override
    public UserResponse buscarPorEmail(String email) {
        User user = userRepository.buscarPorEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com email: " + email));
            return UserResponse.from(user);
    }

    @Override
    public boolean excluir(Long id) {
        User user = userRepository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com ID: " + id));
        userRepository.excluir(user);
        return true;
    }
}