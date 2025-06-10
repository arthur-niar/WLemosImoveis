package com.example.wlimoveis.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "imoveis")
public class Imovel {

    @Id
    private String id;
    private String nome;
    private String descricao;
    private Endereco endereco;
    private String tipo;
    private BigDecimal preco;
    private LocalDateTime dataCadastro;
    private String situacao;
    private double tamanho;

    public Imovel(String id, String nome, String descricao, Endereco endereco, String tipo, BigDecimal preco,
            LocalDateTime dataCadastro, String situacao, double tamanho) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.endereco = endereco;
        this.tipo = tipo;
        this.preco = preco;
        this.dataCadastro = dataCadastro;
        this.situacao = situacao;
        this.tamanho = tamanho;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }
}


// JSON DEFAULT PARA CADASTRO DE IMÓVEL (TEMPLATE)

// {
//   "nome": "Apartamento Luxo",
//   "tipo": "Apartamento",
//   "situacao": "Disponível",
//   "preco": 850000.00,
//   "tamanho": 120.5,
//   "dataCadastro": "2025-06-10T14:30:00",
//   "descricao": "Apartamento com vista para o mar, 3 suítes, 2 vagas de garagem.",
//   "endereco": {
//     "cidade": "Fortaleza",
//     "bairro": "Meireles",
//     "rua": "Av. Beira Mar",
//     "numero": "1500",
//     "complemento": "Apto 1203"
//   }
// }