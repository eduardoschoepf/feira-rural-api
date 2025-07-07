package com.feirarural.api.produto.dto;

import java.math.BigDecimal;
import java.util.List;

import com.feirarural.api.produto.domain.model.Produto;

public record ProdutoResponse(
    Long id, 
    String nome, 
    String descricao, 
    BigDecimal preco, 
    String unidadeMedida, 
    List<String> imagens, 
    Long categoriaId, 
    Long produtorId) {
        public static ProdutoResponse from(Produto produto) {
            return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getUnidadeMedida(),
                produto.getImagens(),
                produto.getCategoriaId(),
                produto.getProdutorId()
            );
        }

}
