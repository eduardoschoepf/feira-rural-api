package com.feirarural.api.categoria.domain.port.in;

import java.util.List;

import com.feirarural.api.categoria.domain.model.Categoria;

public interface CategoriaUseCase {
    List<Categoria> listarTodas();
    Categoria salvar(Categoria categoria);
    Categoria buscarPorId(Long id);
    Categoria atualizar(Long id, Categoria categoria);
    void excluir(Long id);
}
