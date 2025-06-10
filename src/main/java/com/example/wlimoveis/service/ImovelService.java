package com.example.wlimoveis.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.query.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.wlimoveis.model.Imovel;
import com.example.wlimoveis.repository.ImovelRepository;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Imovel salvar(Imovel imovel) {
        try {
            return imovelRepository.save(imovel);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Dados inválidos");
        }
    }

    public Optional<Imovel> atualizar(String imovelId, Imovel atualizado) {
        if (!imovelRepository.existsById(imovelId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Imóvel não encontrado");
        }
        return imovelRepository.findById(imovelId).map(imovel -> {
            imovel.setNome(atualizado.getNome());
            imovel.setTipo(atualizado.getTipo());
            imovel.setSituacao(atualizado.getSituacao());
            imovel.setEndereco(atualizado.getEndereco());
            imovel.setPreco(atualizado.getPreco());
            imovel.setTamanho(atualizado.getTamanho());
            return imovelRepository.save(imovel);
        });
    }

    public boolean excluir(String id) {
        if (!imovelRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Imóvel não encontrado");
        }
        imovelRepository.deleteById(id);
        return true;
    }

    public List<Imovel> buscarPorCampos(String id, String nome, String tipo, String situacao,
            String cidade, String bairro, String rua, String numero, String complemento, BigDecimal preco,
            double tamanho, LocalDateTime dataInicio, LocalDateTime dataFim) {
        Query query = new Query();
        if (id != null)
            query.addCriteria(Criteria.where("id").is(id)
            .regex("^" + Pattern.quote(id) + "$", "i"));
        if (nome != null)
            query.addCriteria(Criteria.where("nome").is(nome)
            .regex("^" + Pattern.quote(nome) + "$", "i"));
        if (tipo != null)
            query.addCriteria(Criteria.where("tipo").is(tipo)
            .regex("^" + Pattern.quote(tipo) + "$", "i"));
        if (situacao != null)
            query.addCriteria(Criteria.where("situacao").is(situacao)
            .regex("^" + Pattern.quote(situacao) + "$", "i"));
        if (cidade != null)
            query.addCriteria(Criteria.where("endereco.cidade").is(cidade)
            .regex("^" + Pattern.quote(cidade) + "$", "i"));
        if (bairro != null)
            query.addCriteria(Criteria.where("endereco.bairro").is(bairro)
            .regex("^" + Pattern.quote(bairro) + "$", "i"));
        if (rua != null)
            query.addCriteria(Criteria.where("endereco.rua").is(rua)
            .regex("^" + Pattern.quote(rua) + "$", "i"));
        if (numero != null)
            query.addCriteria(Criteria.where("endereco.numero").is(numero)
            .regex("^" + Pattern.quote(numero) + "$", "i"));
        if (complemento != null)
            query.addCriteria(Criteria.where("endereco.complemento").is(complemento)
            .regex("^" + Pattern.quote(complemento) + "$", "i"));
        if (preco != null)
            query.addCriteria(Criteria.where("preco").is(preco)
            .regex("^" + Pattern.quote(preco.toString()) + "$", "i"));
        if (tamanho > 0)
            query.addCriteria(Criteria.where("tamanho").is(tamanho)
            .regex("^" + Pattern.quote(String.valueOf(tamanho)) + "$", "i"));
        if (dataInicio != null && dataFim != null)
            query.addCriteria(Criteria.where("dataCadastro").gte(dataInicio).lte(dataFim));
        return mongoTemplate.find(query, Imovel.class);
    }

    public List<Imovel> buscarTodos() {
        return imovelRepository.findAll();
    }
}
