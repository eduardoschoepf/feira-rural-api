package com.feirarural.api.categoria.domain.port.out;

import com.feirarural.api.categoria.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    List<Categoria> listarTodas();
    Categoria salvar(Categoria categoria);
    Optional<Categoria> buscarPorId(Long id);
    void excluir(Categoria categoria);
}
