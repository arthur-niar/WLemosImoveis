package com.example.wlimoveis.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.wlimoveis.model.Corretor;

public interface CorretorRepository extends MongoRepository<Corretor, String> {

    Optional<Corretor> findByUsuarioId(String usuarioId);
    Optional<Corretor> findByCreci(String creci);
    
}
