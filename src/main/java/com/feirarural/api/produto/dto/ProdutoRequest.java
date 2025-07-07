package com.feirarural.api.produto.dto;

import java.math.BigDecimal;
import java.util.List;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRequest(
        @NotBlank(message = "O nome é obrigatório.") 
        String nome,
        String descricao, 
        BigDecimal preco, 
        String unidadeMedida, 
        List<String> imagens, 
        Long categoriaId,
        Long produtorId
        ) {}
