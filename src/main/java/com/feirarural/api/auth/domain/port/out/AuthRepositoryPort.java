package com.feirarural.api.auth.domain.port.out;

import com.feirarural.api.auth.domain.model.AuthInfo;
import java.util.Optional;

public interface AuthRepositoryPort {
    
    /**
     * Salva as informações de autenticação
     * @param authInfo Dados de autenticação a serem salvos
     * @return AuthInfo salvo
     */
    AuthInfo salvar(AuthInfo authInfo);
    
    /**
     * Busca informações de autenticação por ID de usuário
     * @param userId ID do usuário
     * @return Optional contendo as informações de autenticação se encontrado
     */
    Optional<AuthInfo> buscarPorUsuarioId(Long userId);
    
    /**
     * Busca informações de autenticação por refresh token
     * @param refreshToken Token de atualização
     * @return Optional contendo as informações de autenticação se encontrado
     */
    Optional<AuthInfo> buscarPorRefreshToken(String refreshToken);
    
    /**
     * Remove as informações de autenticação pelo ID
     * @param id ID das informações de autenticação a serem removidas
     */
    void excluir(Long id);
}