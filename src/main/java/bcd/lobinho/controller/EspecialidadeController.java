package bcd.lobinho.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bcd.lobinho.model.Especialidade;
import bcd.lobinho.service.EspecialidadeService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadeController {
    private final EspecialidadeService especialidadeService;

    public EspecialidadeController(EspecialidadeService especialidadeService) {
        this.especialidadeService = especialidadeService;
    }

    @GetMapping
    public ResponseEntity<List<Especialidade>> listarTodos() {
        return ResponseEntity.ok(especialidadeService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(especialidadeService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Especialidade> criar(@RequestBody Especialidade especialidade) {
        Especialidade novaEspecialidade = especialidadeService.criar(especialidade);
        return ResponseEntity.created(URI.create("/api/especialidades/" + novaEspecialidade.getId())).body(novaEspecialidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Especialidade> atualizar(@PathVariable Integer id, @RequestBody Especialidade especialidade) {
        return ResponseEntity.ok(especialidadeService.atualizar(id, especialidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        especialidadeService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}