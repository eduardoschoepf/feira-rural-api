package com.feirarural.api.auth.adapter.out.persistence;

import com.feirarural.api.user.adapter.out.persistence.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "auth_infos")
@Getter
@Setter
public class AuthEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
    
    @Column(nullable = false)
    private String refreshToken;
    
    @Column(nullable = false)
    private LocalDateTime expiresAt;
    
    @Column(nullable = false)
    private boolean active;

    // Construtor padrão necessário para JPA
    public AuthEntity() {
    }

    // Construtor completo
    public AuthEntity(UserEntity user, String refreshToken, LocalDateTime expiresAt, boolean active) {
        this.user = user;
        this.refreshToken = refreshToken;
        this.expiresAt = expiresAt;
        this.active = active;
    }
}