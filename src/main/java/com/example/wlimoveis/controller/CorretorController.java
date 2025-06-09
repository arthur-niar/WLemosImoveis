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

import com.example.wlimoveis.model.Corretor;
import com.example.wlimoveis.model.Usuario;
import com.example.wlimoveis.service.CorretorService;

@RestController
@RequestMapping("/corretores")
public class CorretorController {

    @Autowired
    private CorretorService corretorService;

    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Corretor Service está online!");
    }

    @GetMapping("/iscorretor/{usuarioId}")
    public ResponseEntity<Boolean> isCorretor(@PathVariable String usuarioId) {
        boolean isCorretor = corretorService.isCorretor(usuarioId);
        return ResponseEntity.ok(isCorretor);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Corretor corretor) {
        Usuario novo = corretorService.salvar(corretor);
        return ResponseEntity.ok(novo);
    }
    
    @PutMapping("/{usuarioId}") // atualizar
    public ResponseEntity<?> atualizar(@PathVariable String usuarioId,
    @RequestBody Corretor corretorAtualizado) {
        return corretorService.atualizar(usuarioId, corretorAtualizado)
                .map(corretor -> ResponseEntity.ok(corretor))
                .orElse(ResponseEntity.notFound().build());
    } 

    @DeleteMapping("/{usuarioId}") // deletar
    public ResponseEntity<?> excluir(@PathVariable String usuarioId) {
        boolean removido = corretorService.excluir(usuarioId);
        if (removido) {
            return ResponseEntity.ok("Usuário removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/todos")
    public ResponseEntity<Iterable<Corretor>> buscarTodos() {
        Iterable<Corretor> corretores = corretorService.buscarTodos();
        return ResponseEntity.ok(corretores);
    }

    @GetMapping("/{usuarioId}") // buscar por ID
    public ResponseEntity<Corretor> buscarPorId(@PathVariable String usuarioId) {
        return corretorService.buscarPorId(usuarioId)
                .map(corretor -> ResponseEntity.ok(corretor))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}") // buscar por ID de usuário
    public ResponseEntity<Corretor> buscarPorUsuarioId(@PathVariable String usuarioId) {
        return corretorService.buscarPorUsuarioId(usuarioId)
                .map(corretor -> ResponseEntity.ok(corretor))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/creci/{creci}") // buscar por CRECI
    public ResponseEntity<Corretor> buscarPorCreci(@PathVariable String creci) {
        return corretorService.buscarPorCreci(creci)
                .map(corretor -> ResponseEntity.ok(corretor))
                .orElse(ResponseEntity.notFound().build());
    }
}
