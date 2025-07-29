package com.feirarural.api.user.domain.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.feirarural.api.shared.domain.model.Role;

public class User implements UserDetails {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private Role role;  // Alterado de TipoUsuario para Role

    // Construtor completo
    public User(Long id, String nome, String email, String senha, Role role) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    // Construtor sem id (útil para criação)
    public User(String nome, String email, String senha, Role role) {
        this(null, nome, email, senha, role);
    }

    // Getters
    public Long getId() { 
        return id; 
    }
    
    public String getNome() { 
        return nome; 
    }
    
    public String getEmail() { 
        return email; 
    }
    
    public String getSenha() { 
        return senha; 
    }  
    
    public Role getRole() { 
        return role; 
    }

    // Setters
    public void setId(Long id) { 
        this.id = id; 
    }
    
    public void setNome(String nome) { 
        this.nome = nome; 
    }
    
    public void setEmail(String email) { 
        this.email = email; 
    }
    
    public void setSenha(String senha) { 
        this.senha = senha; 
    }
    
    public void setRole(Role role) { 
        this.role = role; 
    }

    // Métodos auxiliares
    public boolean isAdmin() {
        return Role.ADMIN.equals(this.role);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

   @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(() -> "ROLE_" + role.name());
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}