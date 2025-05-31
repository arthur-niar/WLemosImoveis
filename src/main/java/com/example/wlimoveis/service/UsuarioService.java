package com.example.wlimoveis.service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.wlimoveis.model.Usuario;
import com.example.wlimoveis.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public Optional<Usuario> autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        return usuarioOpt.filter(usuario -> usuario.getSenha().equals(senha));
    }

    public Usuario salvar(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Email já cadastrado");
        }
        try {
            return usuarioRepository.save(usuario);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Dados inválidos"); 
        }
    }

    public Optional<Usuario> atualizar(ObjectId id, Usuario atualizado) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Usuário não encontrado"); 
        }
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(atualizado.getNome());
            usuario.setEmail(atualizado.getEmail());
            usuario.setSenha(atualizado.getSenha());
            return usuarioRepository.save(usuario);
        });
    }

    public boolean excluir(ObjectId id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
        return true;
    }

}