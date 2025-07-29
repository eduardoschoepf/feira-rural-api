package com.feirarural.api.auth.adapter.out.persistence;

import com.feirarural.api.auth.application.mapper.AuthMapper;
import com.feirarural.api.auth.domain.model.AuthInfo;
import com.feirarural.api.auth.domain.port.out.AuthRepositoryPort;
import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.out.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthJpaAdapter implements AuthRepositoryPort {

    private final AuthJpaRepository jpaRepository;
    private final UserRepository userRepository;
    private final AuthMapper authMapper;

    public AuthJpaAdapter(AuthJpaRepository jpaRepository, 
                         UserRepository userRepository,
                         AuthMapper authMapper) {
        this.jpaRepository = jpaRepository;
        this.userRepository = userRepository;
        this.authMapper = authMapper;
    }

    @Override
    public AuthInfo salvar(AuthInfo authInfo) {
        User user = userRepository.buscarPorId(authInfo.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        AuthEntity entity = authMapper.toEntity(authInfo, user);
        AuthEntity savedEntity = jpaRepository.save(entity);
        return authMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<AuthInfo> buscarPorUsuarioId(Long userId) {
        return jpaRepository.findByUserId(userId)
                .map(authMapper::toDomain);
    }

    @Override
    public Optional<AuthInfo> buscarPorRefreshToken(String refreshToken) {
        return jpaRepository.findByRefreshToken(refreshToken)
                .map(authMapper::toDomain);
    }

    @Override
    public void excluir(Long id) {
        jpaRepository.deleteById(id);
    }
}