package com.feirarural.api.produto.adapter.in.rest;

import org.springframework.web.bind.annotation.RestController;

import com.feirarural.api.produto.application.mapper.ProdutoMapper;
import com.feirarural.api.produto.domain.model.Produto;
import com.feirarural.api.produto.domain.port.in.ProdutoUseCase;
import com.feirarural.api.produto.dto.ProdutoRequest;
import com.feirarural.api.produto.dto.ProdutoResponse;
import com.feirarural.api.user.domain.port.in.UserUseCase;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    private final ProdutoUseCase produtoUseCase;
    private final ProdutoMapper mapper;
    private final UserUseCase usuario;

    public ProdutoController(ProdutoUseCase produtoUseCase, ProdutoMapper mapper, UserUseCase usuario) {
        this.produtoUseCase = produtoUseCase;
        this.mapper = mapper;
        this.usuario = usuario;
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<ProdutoResponse>> listarTodos() {
         // Verifica o usuário autenticado
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = auth.getName();
        System.out.println("Usuário autenticado: " + emailUsuario);
        
        List<Produto> produtos = produtoUseCase.listarTodos();
        List<ProdutoResponse> responses = produtos.stream()
            .map(mapper::toResponse)
            .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
        Produto produto = produtoUseCase.buscarPorId(id);
        ProdutoResponse response = mapper.toResponse(produto); // Aqui pode estourar
        return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime erro no console
            return ResponseEntity.badRequest().body("Erro: " + e.getMessage());
        }
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('PRODUTOR', 'ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProdutoResponse> criar(
        @RequestBody ProdutoRequest request,
        @AuthenticationPrincipal UserDetails userDetails) {
        Produto produto = mapper.toDomain(request);
        Produto salva = produtoUseCase.salvar(produto);
        // TODO: Obtém o ID do produtor autenticado
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponse> atualizar(@PathVariable Long id, @RequestBody ProdutoRequest request) {
        Produto produto = mapper.toDomain(request);
        Produto atualizada = produtoUseCase.atualizar(id, produto);
        return ResponseEntity.ok(mapper.toResponse(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        produtoUseCase.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
