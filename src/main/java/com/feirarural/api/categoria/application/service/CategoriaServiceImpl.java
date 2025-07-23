package com.feirarural.api.categoria.application.service;

import com.feirarural.api.categoria.domain.model.Categoria;
import com.feirarural.api.categoria.domain.port.in.CategoriaUseCase;
import com.feirarural.api.categoria.domain.port.out.CategoriaRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaUseCase {

    private final CategoriaRepository repository;

    public CategoriaServiceImpl(CategoriaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Categoria> listarTodas() {
        return repository.listarTodas();
    }

    @Override
    public Categoria salvar(Categoria categoria) {
        return repository.salvar(categoria);
    }

    @Override
    public Categoria buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    @Override
    public Categoria atualizar(Long id, Categoria categoria) {
        Categoria existente = buscarPorId(id);
        existente.setNome(categoria.getNome());
        existente.setDescricao(categoria.getDescricao());
        
        return repository.salvar(existente);
    }

    @Override
    public void excluir(Long id) {
        Categoria categoria = buscarPorId(id);
        repository.excluir(categoria);
    }
}
