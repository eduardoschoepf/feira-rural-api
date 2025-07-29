package com.feirarural.api.user.dto;

public record UserRequest(
    String nome,
    String email,
    String senha,
    String role  // Agora como String para facilitar a desserialização
) {}