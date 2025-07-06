package com.feirarural.api.categoria.adapter.out.persistence;

import com.feirarural.api.categoria.domain.model.Categoria;
import jakarta.persistence.*;

@Entity
@Table(name = "categorias")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    private String descricao;

    public CategoriaEntity() {}

    public CategoriaEntity(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public static CategoriaEntity fromDomain(Categoria categoria) {
        return new CategoriaEntity(
            categoria.getId(),
            categoria.getNome(),
            categoria.getDescricao()
        );
    }

    public Categoria toDomain() {
        return new Categoria(id, nome, descricao);
    }

    // Getters e setters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }

    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
