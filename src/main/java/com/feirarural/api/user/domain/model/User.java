package com.feirarural.api.user.domain.model;

public class User {
    private Long id;
    private String nome;
    private String email;
    private String senha;
    private TipoUsuario tipo;

    public User(Long id, String nome, String email, String senha, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenha() { return senha; }  
    public TipoUsuario getTipo() { return tipo; }
    public TipoUsuario getTipoEnum() {return tipo;}
    
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenha(String senha) { this.senha = senha; }
    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }  
}