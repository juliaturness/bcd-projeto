package bcd.lobinho.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bcd.lobinho.model.Insignia;
import bcd.lobinho.service.InsigniaService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/insignias")
public class InsigniaController {
    private final InsigniaService insigniaService;

    public InsigniaController(InsigniaService insigniaService) {
        this.insigniaService = insigniaService;
    }

    @GetMapping
    public ResponseEntity<List<Insignia>> listarTodos() {
        return ResponseEntity.ok(insigniaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insignia> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(insigniaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Insignia> criar(@RequestBody Insignia insignia) {
        Insignia novaInsignia = insigniaService.criar(insignia);
        return ResponseEntity.created(URI.create("/api/insignias/" + novaInsignia.getId()))
                .body(novaInsignia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Insignia> atualizar(@PathVariable Integer id, @RequestBody Insignia insignia) {
        return ResponseEntity.ok(insigniaService.atualizar(id, insignia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        insigniaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}