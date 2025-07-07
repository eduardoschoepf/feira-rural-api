package com.feirarural.api.produto.domain.model;

import java.math.BigDecimal;
import java.util.List;

public class Produto {
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private String unidadeMedida;
    private List<String> imagens;
    private Long categoriaId;
    private Long produtorId;

    public Produto(Long id, String nome, String descricao, BigDecimal preco, String unidadeMedida, List<String> imagens, Long categoriaId, Long produtorId) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.unidadeMedida = unidadeMedida;
        this.imagens = imagens;
        this.categoriaId = categoriaId;
        this.produtorId = produtorId;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public BigDecimal getPreco() { return preco; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public List<String> getImagens() { return imagens;}
    public Long getCategoriaId() { return categoriaId; }
    public Long getProdutorId() { return produtorId; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
    public void setImagens(List<String> imagens) { this.imagens = imagens; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public void setProdutorId(Long produtorId) { this.produtorId = produtorId; }
}