package com.example.wlimoveis.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.wlimoveis.model.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByUsuarioId(String usuarioId);
    
}
