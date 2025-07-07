package com.feirarural.api.user.adapter.out.persistence;

import com.feirarural.api.user.domain.model.User;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senhaCriptografada;
    private String tipo; // "produtor" ou "consumidor"

    public UserEntity() {}

    public UserEntity(Long id, String nome, String email, String senhaCriptografada, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaCriptografada = senhaCriptografada;
        this.tipo = tipo;
    }

    public static UserEntity fromDomain(User user) {
        return new UserEntity(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getSenhaCriptografada(),
            user.getTipo()
        );
    }  
    
    public User toDomain() {
        return new User(
            id,
            nome,
            email,
            senhaCriptografada,
            tipo
        );
    }
}
