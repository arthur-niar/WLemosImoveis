package com.example.wlimoveis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wlimoveis.model.Cliente;
import com.example.wlimoveis.model.Usuario;
import com.example.wlimoveis.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Cliente Service está online!");
    }

    @GetMapping("/iscliente/{usuarioId}")
    public ResponseEntity<Boolean> isCliente(@PathVariable String usuarioId) {
        boolean isCliente = clienteService.isCliente(usuarioId);
        return ResponseEntity.ok(isCliente);
    }
    
    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Cliente cliente) {
        Usuario novo = clienteService.salvar(cliente);
        return ResponseEntity.ok(novo);
    }
    
    @PutMapping("/{usuarioId}") // atualizar
    public ResponseEntity<?> atualizar(@PathVariable String usuarioId, @RequestBody Cliente clienteAtualizado) {
        return clienteService.atualizar(usuarioId, clienteAtualizado)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    } 

    @DeleteMapping("/{usuarioId}") // deletar
    public ResponseEntity<?> excluir(@PathVariable String usuarioId) {
        boolean removido = clienteService.excluir(usuarioId);
        if (removido) {
            return ResponseEntity.ok("Usuário removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<Cliente>> buscarTodos() {
        Iterable<Cliente> clientes = clienteService.buscarTodos();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{usuarioId}") // buscar por ID
    public ResponseEntity<Cliente> buscarPorId(@PathVariable String usuarioId) {
        return clienteService.buscarPorId(usuarioId)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}") // buscar por ID de usuário
    public ResponseEntity<Cliente> buscarPorUsuarioId(@PathVariable String usuarioId) {
        return clienteService.buscarPorUsuarioId(usuarioId)
                .map(cliente -> ResponseEntity.ok(cliente))
                .orElse(ResponseEntity.notFound().build());
    }
}

