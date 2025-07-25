package com.feirarural.api.user.application.service;

import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.in.UserUseCase;
import com.feirarural.api.user.domain.port.out.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserUseCase {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> listarTodos() {
        return repository.listarTodos();
    }

    @Override
    public User salvar(User user) {
        Optional<User> exists = repository.buscarPorEmail(user.getEmail());
        if (exists.isPresent()) {
            throw new IllegalStateException("Email já cadastrado");
        }
        String senhaCriptografada = passwordEncoder.encode(user.getSenha());
        user.setSenha(senhaCriptografada);
        return repository.salvar(user);
    }

    @Override
    public User atualizar(Long id, User request) {
        User user = repository.buscarPorId(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
        user.setNome(request.getNome());
        user.setEmail(request.getEmail());
        user.setTipo(request.getTipo());
        
        String novaSenha = request.getSenha();
        if (novaSenha != null && !novaSenha.isBlank() && !passwordEncoder.matches(novaSenha, user.getSenha())) {
            user.setSenha(passwordEncoder.encode(novaSenha));
        }
        
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

    @Override
    public Optional<User> autenticar(String email, String senha) {
        User usuario = repository
                            .buscarPorEmail(email)
                            .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com email: " + email));
        
        System.out.println("Senha confere? " + passwordEncoder.matches("senha123", usuario.getSenha()));
        
        return repository.buscarPorEmail(email)
                         .filter(user -> passwordEncoder.matches(senha, user.getSenha()));
    }

    @Override
    public Optional<User> buscarPorEmail(String email) {
        return repository.buscarPorEmail(email);
    }
}