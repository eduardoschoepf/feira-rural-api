package com.feirarural.api.user.application.service;

import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.in.UserUseCase;
import com.feirarural.api.user.domain.port.out.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserUseCase {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> listarTodos() {
        return repository.listarTodos();
    }

    @Override
    public User salvar(User user) {
        return repository.salvar(user);
    }

    @Override
    public User atualizar(Long id, User request) {
        User user = repository.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setTipo(request.getTipo());
        user.setSenhaCriptografada(request.getSenhaCriptografada());
        return repository.salvar(user);
    }

    @Override
    public User buscarPorId(Long id) {
        return repository.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
    }

    @Override
    public void excluir(Long id) {
        User user = buscarPorId(id);
        repository.excluir(user);
    }
}