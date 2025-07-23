package com.feirarural.api.produto.application.mapper;

import com.feirarural.api.produto.domain.model.Produto;
import com.feirarural.api.produto.dto.ProdutoRequest;
import com.feirarural.api.produto.dto.ProdutoResponse;

public class ProdutoMapper {

    public Produto toDomain(ProdutoRequest request) {
        // Convert ProdutoRequest to Produto domain model
        return new Produto(null, request.nome(), request.descricao(), request.preco(), request.unidadeMedida(), request.imagens(), request.categoriaId(), request.produtorId());
    }

    // Implement the methods to map between ProdutoRequest, ProdutoResponse, and Produto domain model
    public ProdutoResponse toResponse(Produto produto) {
        // Convert Produto to ProdutoResponse
        return new ProdutoResponse(produto.getId(), produto.getNome(), produto.getDescricao(), produto.getPreco(), produto.getUnidadeMedida(), produto.getImagens(), produto.getCategoriaId(), produto.getProdutorId());
    }
}
