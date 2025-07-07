package com.feirarural.api.produto.domain.port;

import java.util.List;

import com.feirarural.api.produto.dto.ProdutoRequest;
import com.feirarural.api.produto.dto.ProdutoResponse;

public interface ProdutoService {
    List<ProdutoResponse> listarTodos();
    ProdutoResponse salvar(ProdutoRequest request);
    ProdutoResponse buscarPorIdDTO(Long id);
    ProdutoResponse atualizar(Long id, ProdutoRequest request);
    void excluir(Long id);
}
