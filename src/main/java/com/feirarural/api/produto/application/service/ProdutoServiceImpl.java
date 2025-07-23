package com.feirarural.api.produto.application.service;

import com.feirarural.api.produto.domain.model.Produto;
import com.feirarural.api.produto.domain.port.in.ProdutoUseCase;
import com.feirarural.api.produto.domain.port.out.ProdutoRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoUseCase{
    public final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Produto> listarTodos() {
        return repository.listarTodos();
    }

    @Override
    public Produto salvar(Produto produto) {
        return repository.salvar(produto);
    }

   
    
    @Override
    public Produto buscarPorId(Long id) {
        return repository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
    }

    @Override
    public Produto atualizar(Long id, Produto produto) {
        Produto existente = buscarPorId(id);
        existente.setNome(produto.getNome());
        existente.setDescricao(produto.getDescricao());
        existente.setUnidadeMedida(produto.getUnidadeMedida());
        existente.setPreco(produto.getPreco());
        existente.setImagens(produto.getImagens());
        existente.setCategoriaId(produto.getCategoriaId());
        existente.setProdutorId(produto.getProdutorId());
        
        return repository.salvar(existente);
    }

    @Override
    public void excluir(Long id) {
        Produto produto = buscarPorId(id);
        repository.excluir(produto);
    }
}
