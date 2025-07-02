package com.feirarural.api.categoria.domain.port;

import com.feirarural.api.categoria.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    List<Categoria> listarTodas();
    Categoria salvar(Categoria categoria);
    Optional<Categoria> buscarPorId(Long id);
    Categoria buscarPorIdDTO(Long id);
}
