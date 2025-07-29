package com.feirarural.api.user.application.mapper;

import org.springframework.stereotype.Component;

import com.feirarural.api.shared.domain.model.Role;
import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.dto.UserRequest;
import com.feirarural.api.user.dto.UserResponse;

@Component
public class UserMapper {

    public User toDomain(UserRequest request) {
        try {
            // Converte a string para o enum Role
            Role role = request.role() != null ? 
                        Role.valueOf(request.role().toUpperCase()) : 
                        Role.USUARIO; // Valor padrão
            
            return new User(
                null,           // ID é gerado automaticamente
                request.nome(),
                request.email(),
                request.senha(),
                role
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Role de usuário inválido: " + request.role() + 
                   ". Valores permitidos: " + java.util.Arrays.toString(Role.values()));
        }
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
            user.getId(),
            user.getNome(),
            user.getEmail(),
            user.getRole().name(), // Usamos o nome do enum (ADMIN, USUARIO)
            null // Não retornamos a senha no response por segurança
        );
    }
    
    // Método para atualização parcial (patch)
    public User updateFromRequest(UserRequest request, User existingUser) {
        if (request.nome() != null) {
            existingUser.setNome(request.nome());
        }
        
        if (request.email() != null) {
            existingUser.setEmail(request.email());
        }
        
        if (request.senha() != null) {
            existingUser.setSenha(request.senha());
        }
        
        if (request.role() != null) {
            try {
                existingUser.setRole(Role.valueOf(request.role().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Role de usuário inválido: " + request.role());
            }
        }
        
        return existingUser;
    }
}