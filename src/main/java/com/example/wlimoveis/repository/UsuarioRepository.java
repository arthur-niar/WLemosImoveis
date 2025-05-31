package com.example.wlimoveis.repository;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.wlimoveis.model.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {

    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
}
