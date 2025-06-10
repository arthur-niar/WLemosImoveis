package com.example.wlimoveis.repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.wlimoveis.model.Imovel;

public interface ImovelRepository extends MongoRepository<Imovel, String> {

    List<Imovel> findByNome(String nome);
    List<Imovel> findByTipo(String tipo);
    List<Imovel> findBySituacao(String situacao);
    List<Imovel> findByEndereco_Cidade(String cidade);
    List<Imovel> findByEndereco_Bairro(String bairro);
    List<Imovel> findByEndereco_Rua(String rua);
    List<Imovel> findByEndereco_Numero(String numero);
    List<Imovel> findByEndereco_Complemento(String complemento);
    List<Imovel> findByPreco(BigDecimal preco);
    List<Imovel> findByTamanho(double tamanho);
    List<Imovel> findByDataCadastroBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

}
