package bcd.lobinho.controller;
import bcd.lobinho.model.Pessoa;
import bcd.lobinho.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public List<Pessoa> listarTodos() {
        return pessoaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pessoa buscarPorId(@PathVariable Integer id) {
        return pessoaService.buscarPorId(id);
    }

    @PostMapping
    public Pessoa criar(@RequestBody Pessoa pessoa) {
        return pessoaService.salvar(pessoa);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(
            @PathVariable Integer id,
            @RequestBody Pessoa pessoaAtualizada) {

        Pessoa pessoa = pessoaService.atualizar(id, pessoaAtualizada);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        pessoaService.excluir(id);
    }

    @GetMapping("/buscar")
    public List<Pessoa> buscarPorNome(@RequestParam String nome) {
        return pessoaService.buscarPorNome(nome);
    }

    @GetMapping("/cpf/{cpf}")
    public Pessoa buscarPorCpf(@PathVariable String cpf) {
        return pessoaService.buscarPorCpf(cpf);
    }
}
