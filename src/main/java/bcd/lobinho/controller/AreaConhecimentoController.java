package bcd.lobinho.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import bcd.lobinho.model.AreaConhecimento;
import bcd.lobinho.service.AreaConhecimentoService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/areas-conhecimento")
public class AreaConhecimentoController {
    private final AreaConhecimentoService areaConhecimentoService;

    public AreaConhecimentoController(AreaConhecimentoService areaConhecimentoService) {
        this.areaConhecimentoService = areaConhecimentoService;
    }

    @GetMapping
    public ResponseEntity<List<AreaConhecimento>> listarTodos() {
        return ResponseEntity.ok(areaConhecimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaConhecimento> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(areaConhecimentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AreaConhecimento> criar(@RequestBody AreaConhecimento areaConhecimento) {
        AreaConhecimento novaArea = areaConhecimentoService.criar(areaConhecimento);
        return ResponseEntity.created(URI.create("/api/areas-conhecimento/" + novaArea.getId())).body(novaArea);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaConhecimento> atualizar(@PathVariable Integer id, @RequestBody AreaConhecimento areaConhecimento) {
        return ResponseEntity.ok(areaConhecimentoService.atualizar(id, areaConhecimento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        areaConhecimentoService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}