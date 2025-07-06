package com.feirarural.api.categoria.application.service;

import com.feirarural.api.categoria.domain.model.Categoria;
import com.feirarural.api.categoria.domain.port.CategoriaRepository;
import com.feirarural.api.categoria.domain.port.CategoriaService;
import com.feirarural.api.categoria.dto.CategoriaRequest;
import com.feirarural.api.categoria.dto.CategoriaResponse;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public CategoriaResponse salvar(CategoriaRequest request) {
        Categoria categoria = new Categoria(null, request.nome(), request.descricao());
        Categoria salva = repository.salvar(categoria);
        return new CategoriaResponse(salva.getId(), salva.getNome(), salva.getDescricao());
    }

    @Override
    public List<CategoriaResponse> listarTodas() {
        return repository.listarTodas().stream()
                .map(c -> new CategoriaResponse(c.getId(), c.getNome(), c.getDescricao()))
                .toList();
    }

    @Override
    public CategoriaResponse buscarPorIdDTO(Long id) {
        Categoria categoria = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
        return new CategoriaResponse(categoria.getId(), categoria.getNome(), categoria.getDescricao());
    }

    @Override
    public CategoriaResponse atualizar(Long id, CategoriaRequest request) {
        Categoria categoria = repository.buscarPorId(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id));

        categoria.setNome(request.nome());
        categoria.setDescricao(request.descricao());

        Categoria atualizada = repository.salvar(categoria);
        return new CategoriaResponse(atualizada.getId(), atualizada.getNome(), atualizada.getDescricao());
    }

    @Override
    public void excluir(Long id) {
        Categoria categoria = repository.buscarPorId(id)
            .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada com ID: " + id));

        repository.excluir(categoria);
    }
}
