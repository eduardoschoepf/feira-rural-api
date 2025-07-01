package com.feirarural.api.service;

import com.feirarural.api.model.Categoria;
import com.feirarural.api.repository.CategoriaRepository;
import com.feirarural.api.dto.CategoriaRequest;
import com.feirarural.api.dto.CategoriaResponse;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaResponse> listarTodas() {
        return categoriaRepository.findAll()
                .stream()
                .map(c -> new CategoriaResponse(c.getId(), c.getNome(), c.getDescricao()))
                .toList();
    }

    public CategoriaResponse salvar(CategoriaRequest dto) {
        Categoria categoria = Categoria.builder()
            .nome(dto.nome())
            .descricao(dto.descricao())
            .build();
        
        Categoria salva = categoriaRepository.save(categoria);
        return new CategoriaResponse(salva.getId(), salva.getNome(), salva.getDescricao());
    }

    public CategoriaResponse buscarPorIdDTO(Long id) {
        Categoria categoria = categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        
        return new CategoriaResponse(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

}
