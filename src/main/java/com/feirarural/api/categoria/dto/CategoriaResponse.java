// CategoriaResponse.java
package com.feirarural.api.categoria.dto;

import com.feirarural.api.categoria.domain.model.Categoria;

public record CategoriaResponse(
    Long id,
    String nome,
    String descricao
) {
    public static CategoriaResponse from(Categoria categoria) {
        return new CategoriaResponse(
            categoria.getId(),
            categoria.getNome(),
            categoria.getDescricao()
        );
    }
}
