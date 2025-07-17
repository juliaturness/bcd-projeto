package bcd.lobinho.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bcd.lobinho.model.Acampamento;
import bcd.lobinho.service.AcampamentoService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/acampamentos")
public class AcampamentoController {
    private final AcampamentoService acampamentoService;

    public AcampamentoController(AcampamentoService acampamentoService) {
        this.acampamentoService = acampamentoService;
    }

    @GetMapping
    public ResponseEntity<List<Acampamento>> listarTodos() {
        return ResponseEntity.ok(acampamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acampamento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(acampamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Acampamento> criar(@RequestBody Acampamento acampamento) {
        Acampamento novoAcampamento = acampamentoService.criar(acampamento);
        return ResponseEntity.created(URI.create("/api/acampamentos/" + novoAcampamento.getId())).body(novoAcampamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acampamento> atualizar(@PathVariable Integer id, @RequestBody Acampamento acampamento) {
        return ResponseEntity.ok(acampamentoService.atualizar(id, acampamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        acampamentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}