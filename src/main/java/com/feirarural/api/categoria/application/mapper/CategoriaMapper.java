package com.feirarural.api.categoria.application.mapper;

import org.springframework.stereotype.Component;

import com.feirarural.api.categoria.domain.model.Categoria;
import com.feirarural.api.categoria.dto.CategoriaRequest;
import com.feirarural.api.categoria.dto.CategoriaResponse;

@Component
public class CategoriaMapper {

    public Categoria toDomain(CategoriaRequest request) {
        return new Categoria(null, request.nome(), request.descricao());
    }

    public CategoriaResponse toResponse(Categoria categoria) {
        return new CategoriaResponse(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }
}
