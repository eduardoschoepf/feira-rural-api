package com.feirarural.api.produto.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.feirarural.api.produto.domain.model.Produto;
import com.feirarural.api.produto.domain.port.ProdutoRepository;
import com.feirarural.api.produto.domain.port.ProdutoService;
import com.feirarural.api.produto.dto.ProdutoRequest;
import com.feirarural.api.produto.dto.ProdutoResponse;

@Service
public class ProdutoServiceImpl implements ProdutoService{
    public final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProdutoResponse salvar(ProdutoRequest request) {
        Produto produto = new Produto(null, request.nome(), request.descricao(), request.preco(), request.unidadeMedida(), request.imagens(), request.produtorId(), request.categoriaId());
        Produto salva = repository.salvar(produto);
        return ProdutoResponse.from(salva);
    }

    @Override
    public List<ProdutoResponse> listarTodos() {
        return repository.listarTodos().stream()
                .map(ProdutoResponse::from)
                .toList();
    }
    
    @Override
    public ProdutoResponse buscarPorIdDTO(Long id) {
        Produto produto = repository.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        return ProdutoResponse.from(produto);
    }
    @Override
    public ProdutoResponse atualizar(Long id, ProdutoRequest request) {
        Produto produto = repository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setUnidadeMedida(request.unidadeMedida());
        produto.setImagens(request.imagens());
        produto.setProdutorId(request.produtorId());
        produto.setCategoriaId(request.categoriaId());

        Produto atualizada = repository.salvar(produto);
        return ProdutoResponse.from(atualizada);
    }

    @Override
    public void excluir(Long id) {
        Produto produto = repository.buscarPorId(id)
            .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));
        repository.excluir(produto);
    }
}
