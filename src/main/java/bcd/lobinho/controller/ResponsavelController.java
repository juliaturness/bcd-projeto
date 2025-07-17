package bcd.lobinho.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bcd.lobinho.model.Responsavel;
import bcd.lobinho.service.ResponsavelService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/responsaveis")
public class ResponsavelController {
    private final ResponsavelService responsavelService;

    public ResponsavelController(ResponsavelService responsavelService) {
        this.responsavelService = responsavelService;
    }

    @GetMapping
    public ResponseEntity<List<Responsavel>> listarTodos() {
        return ResponseEntity.ok(responsavelService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(responsavelService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Responsavel> criar(@RequestBody Responsavel responsavel) {
        Responsavel novoResponsavel = responsavelService.criar(responsavel);
        return ResponseEntity.created(URI.create("/api/responsaveis/" + novoResponsavel.getId())).body(novoResponsavel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> atualizar(@PathVariable Integer id, @RequestBody Responsavel responsavel) {
        return ResponseEntity.ok(responsavelService.atualizar(id, responsavel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        responsavelService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}