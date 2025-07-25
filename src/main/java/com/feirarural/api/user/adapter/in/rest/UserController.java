package com.feirarural.api.user.adapter.in.rest;

import com.feirarural.api.user.application.mapper.UserMapper;
import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.in.UserUseCase;
import com.feirarural.api.user.dto.UserRequest;
import com.feirarural.api.user.dto.UserResponse;

import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserUseCase userUseCase;
    private final UserMapper mapper;

    public UserController(UserUseCase userUseCase, UserMapper mapper) {
        this.userUseCase = userUseCase;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listarTodos() {
        List<User> users = userUseCase.listarTodos();
        List<UserResponse> responses = users.stream()
                .map(mapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> buscarPorId(@PathVariable Long id) {
        User user = userUseCase.buscarPorId(id);
        return ResponseEntity.ok(mapper.toResponse(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> atualizar(@PathVariable Long id, @RequestBody UserRequest request) {
        User user = mapper.toDomain(request);
        User atualizada = userUseCase.atualizar(id, user);
        return ResponseEntity.ok(mapper.toResponse(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        userUseCase.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/inscricao")
    public ResponseEntity<UserResponse> criar(@RequestBody UserRequest request) {
        User user = mapper.toDomain(request);
        User salva = userUseCase.salvar(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(salva));
    }
}
