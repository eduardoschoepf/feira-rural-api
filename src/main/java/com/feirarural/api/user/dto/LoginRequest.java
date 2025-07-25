package com.feirarural.api.user.dto;

public record LoginRequest(
    String email,
    String senha
) {}
