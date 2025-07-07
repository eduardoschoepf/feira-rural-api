package com.feirarural.api.produto.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.List;

import com.feirarural.api.produto.domain.model.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;  
      
    private String descricao;
    private String unidadeMedida;
    private Double preco;
    private String imagemUrl;
    private Long categoriaId;
    private Long produtorId;


    public ProdutoEntity() {}

    public ProdutoEntity(Long id, String nome, String descricao, String unidadeMedida, Double preco, String imagemUrl, Long categoriaId, Long produtorId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.unidadeMedida = unidadeMedida;
        this.preco = preco;
        this.imagemUrl = imagemUrl;
        this.categoriaId = categoriaId;
        this.produtorId = produtorId;
    }

    public static ProdutoEntity fromDomain(Produto produto) {
        return new ProdutoEntity(
            produto.getId(),
            produto.getNome(),
            produto.getDescricao(),
            produto.getUnidadeMedida(),
            produto.getPreco().doubleValue(),
            String.join(",", produto.getImagens()), // Assuming imagens is a List<String>
            produto.getCategoriaId(),
            produto.getProdutorId()
        );
    }

    public Produto toDomain() {
        return new Produto(
            id,
            nome,
            descricao,
            BigDecimal.valueOf(preco),
            unidadeMedida,
            List.of(imagemUrl.split(",")), // Assuming imagens is a List<String>
            categoriaId,
            produtorId
        );
    }
}
