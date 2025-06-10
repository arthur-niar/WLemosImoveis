package com.example.wlimoveis.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.wlimoveis.model.Imovel;
import com.example.wlimoveis.service.ImovelService;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    
    @Autowired
    private ImovelService imovelService;

    @GetMapping("/teste")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Imóveis Service está online!");
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Imovel> cadastrar(@RequestBody Imovel imovel) {
        Imovel novo = imovelService.salvar(imovel);
        return ResponseEntity.ok(novo);
    }

    @PutMapping("/{id}") // atualizar
    public ResponseEntity<?> atualizar(@PathVariable String id,
     @RequestBody Imovel imovelAtualizado) {
        return imovelService.atualizar(id, imovelAtualizado)
                .map(imovel -> ResponseEntity.ok(imovel))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}") // deletar
    public ResponseEntity<?> excluir(@PathVariable String id) {
        boolean removido = imovelService.excluir(id);
        if (removido) {
            return ResponseEntity.ok("Usuário removido com sucesso.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarPorCampos(
            @RequestParam(required = false) String imovelId,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String tipo,
            @RequestParam(required = false) String situacao,
            @RequestParam(required = false) String cidade,
            @RequestParam(required = false) String bairro,
            @RequestParam(required = false) String rua,
            @RequestParam(required = false) String numero,
            @RequestParam(required = false) String complemento,
            @RequestParam(required = false) BigDecimal preco,
            @RequestParam(required = false) Double tamanho,
            @RequestParam(required = false) 
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicio,
            @RequestParam(required = false) 
                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFim) {
        List<Imovel> resultado = imovelService.buscarPorCampos(
                imovelId, nome, tipo, situacao, cidade, bairro, rua, numero, complemento, preco,
                tamanho != null ? tamanho : 0, dataInicio, dataFim);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Imovel>> buscarTodos() {
        List<Imovel> imoveis = imovelService.buscarTodos();
        return ResponseEntity.ok(imoveis);
    }
}
