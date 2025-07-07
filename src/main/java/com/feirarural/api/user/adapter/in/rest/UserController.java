package com.feirarural.api.user.adapter.in.rest;

import com.feirarural.api.produto.dto.ProdutoRequest;
import com.feirarural.api.produto.dto.ProdutoResponse;
import com.feirarural.api.user.domain.model.User;
import com.feirarural.api.user.domain.port.UserService;
import com.feirarural.api.user.dto.UserRequest;
import com.feirarural.api.user.dto.UserResponse;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponse> criar(@Valid @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listarTodos() {
        List<UserResponse> users = userService.listarTodos();
        return ResponseEntity.ok(users);
    }
}
