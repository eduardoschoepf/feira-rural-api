package com.feirarural.api.auth.dto;

public record RegisterRequest(
    String nome,
    String email,
    String senha
) {}