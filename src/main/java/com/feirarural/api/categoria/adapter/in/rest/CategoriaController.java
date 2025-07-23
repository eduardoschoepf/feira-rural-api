package com.feirarural.api.categoria.adapter.in.rest;

import com.feirarural.api.categoria.dto.CategoriaRequest;
import com.feirarural.api.categoria.dto.CategoriaResponse;
import com.feirarural.api.categoria.application.mapper.CategoriaMapper;
import com.feirarural.api.categoria.domain.model.Categoria;
import com.feirarural.api.categoria.domain.port.in.CategoriaUseCase; 

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaUseCase categoriaUseCase;
    private final CategoriaMapper mapper;

    public CategoriaController(CategoriaUseCase categoriaUseCase, CategoriaMapper mapper) {
        this.categoriaUseCase = categoriaUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listarTodas() {
        List<Categoria> categorias = categoriaUseCase.listarTodas();
        List<CategoriaResponse> responses = categorias.stream()
            .map(mapper::toResponse)
            .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> buscarPorId(@PathVariable Long id) {
        Categoria categoria = categoriaUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(categoria));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponse> criar(@RequestBody CategoriaRequest request) {
        Categoria categoria = mapper.toDomain(request);
        Categoria salva = categoriaUseCase.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponse> atualizar(@PathVariable Long id, @RequestBody CategoriaRequest request) {
        Categoria categoria = mapper.toDomain(request);
        Categoria atualizada = categoriaUseCase.atualizar(id, categoria);
        return ResponseEntity.ok(mapper.toResponse(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        categoriaUseCase.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
