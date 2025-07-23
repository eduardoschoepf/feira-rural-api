package com.feirarural.api.produto.domain.port.out;

import java.util.List;
import java.util.Optional;

import com.feirarural.api.produto.domain.model.Produto;

public interface ProdutoRepository {
    List<Produto> listarTodos();
    Produto salvar(Produto produto);
    Optional<Produto> buscarPorId(Long id);
    Produto buscarPorIdDTO(Long id);
    void excluir(Produto produto);
}