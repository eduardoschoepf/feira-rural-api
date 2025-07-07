package com.feirarural.api.produto.adapter.in.rest;

import org.springframework.web.bind.annotation.RestController;
import com.feirarural.api.produto.domain.port.ProdutoService;
import com.feirarural.api.produto.dto.ProdutoRequest;
import com.feirarural.api.produto.dto.ProdutoResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@RequestParam Long id) {
        return ResponseEntity.ok(produtoService.buscarPorIdDTO(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@RequestParam Long id, @RequestBody ProdutoRequest request) {
        ProdutoResponse response = produtoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@RequestParam Long id) {
        produtoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoRequest request) {
        return ResponseEntity.ok(produtoService.salvar(request));
    }
}
