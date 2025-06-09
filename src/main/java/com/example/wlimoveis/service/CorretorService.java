package com.example.wlimoveis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.wlimoveis.model.Corretor;
import com.example.wlimoveis.model.Usuario;
import com.example.wlimoveis.repository.CorretorRepository;
import com.example.wlimoveis.repository.UsuarioRepository;

@Service
public class CorretorService {

    @Autowired
    private CorretorRepository corretorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isCorretor(String usuarioId) {
        return corretorRepository.existsById(usuarioId);
    }

        public Corretor salvar(Corretor corretor) {
        if (corretorRepository.existsById(corretor.getUsuarioId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Corretor já cadastrado");
        }
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(corretor.getUsuarioId());
            if (usuarioOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Usuário não encontrado");
            }
            Usuario usuario = usuarioOpt.get();

            corretor.setNome(usuario.getNome());
            corretor.setEmail(usuario.getEmail());
            corretor.setSenha(usuario.getSenha());
            corretor.setTelefone(corretor.getTelefone());
            corretor.setCreci(corretor.getCreci());

            return corretorRepository.save(corretor);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Dados inválidos");
        }
    }

    public Optional<Corretor> atualizar(String usuarioId, Corretor atualizado) {
        if (!corretorRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Cliente não encontrado");
        }
        return corretorRepository.findById(usuarioId).map(corretor -> {
            corretor.setNome(atualizado.getNome());
            corretor.setEmail(atualizado.getEmail());
            corretor.setSenha(atualizado.getSenha());
            corretor.setTelefone(atualizado.getTelefone());
            corretor.setCreci(atualizado.getCreci());
            return corretorRepository.save(corretor);
        });
    }

    public boolean excluir (String usuarioId) {
        if (!corretorRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Corretor não encontrado");
        }
        corretorRepository.deleteById(usuarioId);
        return true;
    }

    public Optional<Corretor> buscarPorId(String usuarioId) {
        return corretorRepository.findById(usuarioId);
    }

    public Optional<Corretor> buscarPorUsuarioId(String usuarioId) {
        return corretorRepository.findByUsuarioId(usuarioId);
    }

    public Iterable<Corretor> buscarTodos() { return corretorRepository.findAll(); }

    public Optional<Corretor> buscarPorCreci(String creci) {
        return corretorRepository.findByCreci(creci);
    }
   
}
