package com.feirarural.api.user.adapter.out.persistence;

import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.out.UserRepository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserJpaAdapter implements UserRepository {

    private final UserJpaRepository jpaRepository;

    public UserJpaAdapter(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User salvar(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        UserEntity saved = jpaRepository.save(entity);
        return saved.toDomain();
    }

    @Override
    public Optional<User> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public List<User> listarTodos() {
        return jpaRepository.findAll().stream()
                .map(UserEntity::toDomain)
                .toList();
    }

    @Override
    public void excluir(User user) {
        UserEntity entity = UserEntity.fromDomain(user);
        jpaRepository.delete(entity);
    }

    @Override
    public Optional<User> buscarPorEmail(String email) {
        return jpaRepository.findByEmail(email)
                .map(UserEntity::toDomain);
    }
}
