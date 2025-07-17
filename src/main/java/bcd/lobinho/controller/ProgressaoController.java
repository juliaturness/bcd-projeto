package bcd.lobinho.controller;

import bcd.lobinho.model.DesafioDistintivoFeito;
import bcd.lobinho.model.DesafioEspecialidadeFeito;
import bcd.lobinho.model.DesafioInsigniaFeito;
import bcd.lobinho.service.ProgressaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/progressao")
public class ProgressaoController {

    private final ProgressaoService progressaoService;

    public ProgressaoController(ProgressaoService progressaoService) {
        this.progressaoService = progressaoService;
    }

    @PostMapping("/distintivos/{idPessoa}/desafio/{idDesafio}")
    public ResponseEntity<DesafioDistintivoFeito> registrarDesafioDistintivo(
            @PathVariable Integer idPessoa,
            @PathVariable Integer idDesafio,
            @RequestBody DesafioDistintivoFeito desafioFeito) {

        DesafioDistintivoFeito novoDesafio = progressaoService.registrarDesafioDistintivo(idPessoa, idDesafio, desafioFeito);

        return ResponseEntity.created(URI.create("/api/progressao/distintivos/" + novoDesafio.getId())).body(novoDesafio);
    }

    @GetMapping("/distintivos/{idPessoa}")
    public ResponseEntity<List<DesafioDistintivoFeito>> listarDistintivos(@PathVariable Integer idPessoa) {
        List<DesafioDistintivoFeito> desafios = progressaoService.listarDesafiosDistintivosPorPessoa(idPessoa);
        return ResponseEntity.ok(desafios);
    }

    @PostMapping("/insignias/{idPessoa}/desafio/{idDesafio}")
    public ResponseEntity<DesafioInsigniaFeito> registrarDesafioInsignia(
            @PathVariable Integer idPessoa,
            @PathVariable Integer idDesafio,
            @RequestBody DesafioInsigniaFeito desafioFeito) {

        DesafioInsigniaFeito novoDesafio = progressaoService.registrarDesafioInsignia(idPessoa, idDesafio, desafioFeito);

        return ResponseEntity.created(URI.create("/api/progressao/insignias/" + novoDesafio.getId())).body(novoDesafio);
    }

    @GetMapping("/insignias/{idPessoa}")
    public ResponseEntity<List<DesafioInsigniaFeito>> listarInsignias(@PathVariable Integer idPessoa) {
        List<DesafioInsigniaFeito> desafios = progressaoService.listarDesafiosInsigniasPorPessoa(idPessoa);
        return ResponseEntity.ok(desafios);
    }

    @PostMapping("/especialidades/{idPessoa}/desafio/{idDesafio}")
    public ResponseEntity<DesafioEspecialidadeFeito> registrarDesafioEspecialidade(
            @PathVariable Integer idPessoa,
            @PathVariable Integer idDesafio,
            @RequestBody DesafioEspecialidadeFeito desafioFeito) {

        DesafioEspecialidadeFeito novoDesafio = progressaoService.registrarDesafioEspecialidade(idPessoa, idDesafio, desafioFeito);

        return ResponseEntity.created(URI.create("/api/progressao/especialidades/" + novoDesafio.getId())).body(novoDesafio);
    }

    @GetMapping("/especialidades/{idPessoa}")
    public ResponseEntity<List<DesafioEspecialidadeFeito>> listarEspecialidades(@PathVariable Integer idPessoa) {
        List<DesafioEspecialidadeFeito> desafios = progressaoService.listarDesafiosEspecialidadesPorPessoa(idPessoa);
        return ResponseEntity.ok(desafios);
    }

    @GetMapping("/verificar-progressao/{idPessoa}")
    public ResponseEntity<Boolean> verificarProgressao(@PathVariable Integer idPessoa) {
        boolean progressaoCompleta = progressaoService.verificarProgressaoLobinho(idPessoa);
        return ResponseEntity.ok(progressaoCompleta);
    }
}