package com.feirarural.api.categoria.domain.port;

import java.util.List;

import com.feirarural.api.categoria.dto.CategoriaRequest;
import com.feirarural.api.categoria.dto.CategoriaResponse;

public interface CategoriaService {
    List<CategoriaResponse> listarTodas();
    CategoriaResponse salvar(CategoriaRequest dto);
    CategoriaResponse buscarPorIdDTO(Long id);
}