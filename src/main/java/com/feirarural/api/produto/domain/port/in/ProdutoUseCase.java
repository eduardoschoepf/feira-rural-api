package com.feirarural.api.produto.domain.port.in;

import java.util.List;

import com.feirarural.api.produto.domain.model.Produto;

public interface ProdutoUseCase {
    List<Produto> listarTodos();
    Produto salvar(Produto produto);
    Produto buscarPorId(Long id);
    Produto atualizar(Long id, Produto produto);
    void excluir(Long id);
}
