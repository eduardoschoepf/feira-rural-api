package com.feirarural.api.user.application.service;

import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.in.UserUseCase;
import com.feirarural.api.user.domain.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserUseCase {
    private final UserRepository userRepository;

    @Override
    public Optional<User> buscarPorEmail(String email) {
        return userRepository.buscarPorEmail(email);
    }

    @Override
    public User salvar(User user) {
        return userRepository.salvar(user);
    }
}