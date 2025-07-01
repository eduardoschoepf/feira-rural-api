package com.feirarural.api.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaRequest(
    @NotBlank(message = "O nome é obrigatório.")
    String nome,
    String descricao
    ) {}
