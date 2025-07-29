package com.feirarural.api.auth.adapter.out.persistence;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.feirarural.api.user.adapter.out.persistence.UserEntity;

public interface AuthJpaRepository extends JpaRepository<AuthEntity, Long> {  // Corrigido para AuthEntity
    Optional<AuthEntity> findByUserId(Long userId);
    
    Optional<AuthEntity> findByToken(String token);

     // Busca por refresh token
    Optional<AuthEntity> findByRefreshToken(String refreshToken);

    // Busca por usuário ativo
    Optional<AuthEntity> findByUserAndActiveTrue(UserEntity user);

    // Inativação por token
    @Modifying
    @Query("UPDATE AuthEntity a SET a.active = false WHERE a.refreshToken = :refreshToken")
    void deactivateByRefreshToken(@Param("refreshToken") String refreshToken);
}