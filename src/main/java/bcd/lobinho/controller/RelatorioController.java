package bcd.lobinho.controller;


import bcd.lobinho.model.Pessoa;
import bcd.lobinho.model.dto.JovemProgressaoDTO;
import bcd.lobinho.service.RelatorioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;

    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @GetMapping("/jovem/{id}")
    public Pessoa getDadosBiograficos(@PathVariable Long id) {
        return relatorioService.getDadosBiograficos(id);
    }

    @GetMapping("/especialidade/{id}")
    public List<Pessoa> getJovensPorEspecialidade(@PathVariable Long id) {
        return relatorioService.getJovensPorEspecialidade(id);
    }

    @GetMapping("/progressao/{id}")
    public JovemProgressaoDTO getEspecialidadesEInsignias(@PathVariable Long id) {
        return relatorioService.getEspecialidadesEInsignias(id);
    }

    @GetMapping("/requisitos/{jovemId}/{especialidadeId}")
    public List<Object[]> getRequisitosCompletos(
            @PathVariable Long jovemId,
            @PathVariable Long especialidadeId) {
        return relatorioService.getRequisitosCompletos(jovemId, especialidadeId);
    }

    @GetMapping("/cruzeiro-sul")
    public List<Pessoa> getJovensAptosCruzeiroDoSul() {
        return relatorioService.getJovensAptosCruzeiroDoSul();
    }
}