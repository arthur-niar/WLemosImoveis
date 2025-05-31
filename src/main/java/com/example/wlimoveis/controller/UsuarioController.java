package com.example.wlimoveis.controller;

import org.bson.types.ObjectId;
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

import com.example.wlimoveis.dto.request.LoginDTO;
import com.example.wlimoveis.model.Usuario;
import com.example.wlimoveis.service.UsuarioService;

@RestController
@RequestMapping("/usuarios") // por enquanto é só teste
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Usuário Service está online!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO login) {
        return usuarioService.autenticar(login.getEmail(), login.getSenha())
                .map(usuario -> ResponseEntity.ok("Login bem-sucedido!"))
                .orElse(ResponseEntity.status(401)
                .body("Email ou senha inválidos"));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novo = usuarioService.salvar(usuario);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}") // atualizar
    public ResponseEntity<?> atualizar(@PathVariable ObjectId id, @RequestBody Usuario usuarioAtualizado) {
        return usuarioService.atualizar(id, usuarioAtualizado)
                .map(usuario -> ResponseEntity.ok(usuario))
                .orElse(ResponseEntity.notFound().build());
    } 

    @DeleteMapping("/{id}") // deletar
    public ResponseEntity<?> excluir(@PathVariable ObjectId id) {
        boolean removido = usuarioService.excluir(id);
        if (removido) {
            return ResponseEntity.ok("Usuário removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
