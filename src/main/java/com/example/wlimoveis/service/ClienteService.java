package com.example.wlimoveis.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.wlimoveis.model.Cliente;
import com.example.wlimoveis.model.Usuario;
import com.example.wlimoveis.repository.ClienteRepository;
import com.example.wlimoveis.repository.UsuarioRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isCliente(String usuarioId) {
        return clienteRepository.existsById(usuarioId);
    }

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getUsuarioId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Cliente já cadastrado");
        }
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findById(cliente.getUsuarioId());
            if (usuarioOpt.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Usuário não encontrado");
            }
            Usuario usuario = usuarioOpt.get();

            cliente.setNome(usuario.getNome());
            cliente.setEmail(usuario.getEmail());
            cliente.setSenha(usuario.getSenha());

            return clienteRepository.save(cliente);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
            "Dados inválidos");
        }
    }

    public Optional<Cliente> atualizar(String clienteId, Cliente atualizado) {
        if (!clienteRepository.existsById(clienteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Cliente não encontrado");
        }
        return clienteRepository.findById(clienteId).map(cliente -> {
            cliente.setNome(atualizado.getNome());
            cliente.setEmail(atualizado.getEmail());
            cliente.setSenha(atualizado.getSenha());
            cliente.setTelefone(atualizado.getTelefone());
            cliente.setEndereco(atualizado.getEndereco());
            return clienteRepository.save(cliente);
        });
    }

    public boolean excluir (String usuarioId) {
        if (!clienteRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
            "Cliente não encontrado");
        }
        clienteRepository.deleteById(usuarioId);
        return true;
    }

    public Optional<Cliente> buscarPorId(String clienteId) {
 return clienteRepository.findById(clienteId);
    }

    public Optional<Cliente> buscarPorUsuarioId(String usuarioId) {
        return clienteRepository.findByUsuarioId(usuarioId);
    }

    public Iterable<Cliente> buscarTodos() { return clienteRepository.findAll(); }
}
