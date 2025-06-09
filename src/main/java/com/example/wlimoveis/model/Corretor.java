package com.example.wlimoveis.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "corretores")
public class Corretor extends Usuario {

    private String telefone;
    private String creci;

    public Corretor(String usuarioId, String nome, String email,
     String senha, String telefone, String creci) {
        super(usuarioId, nome, email, senha);
        this.telefone = telefone;
        this.creci = creci;
    }

    public String getUsuarioId () {
        return super.getUsuarioId();
    }
    public void setUsuarioId(String usuarioId) {
        super.setUsuarioId(usuarioId);
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCreci() {
        return creci;
    }

    public void setCreci(String creci) {
        this.creci = creci;
    }
}
