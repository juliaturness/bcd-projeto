package bcd.lobinho.controller;


import bcd.lobinho.model.Distintivo;
import bcd.lobinho.service.DistintivoService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/distintivos")
public class DistintivoController {
    private final DistintivoService distintivoService;

    public DistintivoController(DistintivoService distintivoService) {
        this.distintivoService = distintivoService;
    }

    @GetMapping
    public List<Distintivo> listarTodos() {
        return distintivoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Distintivo buscarPorId(@PathVariable Integer id) {
        return distintivoService.buscarPorId(id);
    }

    @PostMapping
    public Distintivo criar(@RequestBody Distintivo distintivo) {
        return distintivoService.salvar(distintivo);
    }

    @PutMapping("/{id}")
    public Distintivo atualizar(@PathVariable Integer id, @RequestBody Distintivo distintivo) {
        distintivo.setId(id);
        return distintivoService.salvar(distintivo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        distintivoService.excluir(id);
    }

    @GetMapping("/buscar")
    public List<Distintivo> buscarPorNome(@RequestParam String nome) {
        return distintivoService.buscarPorNome(nome);
    }
}