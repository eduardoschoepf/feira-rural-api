package com.feirarural.api.user.domain.model;

public class User {
    private Long id;
    private String nome;
    private String email;
    private String senhaCriptografada;
    private String tipo; // "produtor" ou "consumidor"

    public User(Long id, String nome, String email, String senhaCriptografada, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senhaCriptografada = senhaCriptografada;
        this.tipo = tipo;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getSenhaCriptografada() { return senhaCriptografada; }  
    public String getTipo() { return tipo; }
    
    public void setId(Long id) { this.id = id; }
    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setSenhaCriptografada(String senhaCriptografada) { this.senhaCriptografada = senhaCriptografada; }
    public void setTipo(String tipo) { this.tipo = tipo; }  
}