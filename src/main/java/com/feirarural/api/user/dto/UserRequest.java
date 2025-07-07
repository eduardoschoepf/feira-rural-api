package com.feirarural.api.user.dto;

public record UserRequest(
    String nome,
    String email,
    String senhaCriptografada,
    String tipo // "produtor" ou "consumidor"
) {}
