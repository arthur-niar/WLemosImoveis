package com.example.wlimoveis.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clientes")
public class Cliente extends Usuario {

    private String telefone;
    private String endereco;

    public Cliente(String usuarioId, String nome, String email, String senha, String telefone, String endereco) {
        super(usuarioId, nome, email, senha);
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
