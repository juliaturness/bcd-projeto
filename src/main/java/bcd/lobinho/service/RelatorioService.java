package bcd.lobinho.service;

import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.*;
import bcd.lobinho.model.dto.JovemProgressaoDTO;
import bcd.lobinho.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    private final PessoaRepository pessoaRepository;
    private final EspecialidadeRepository especialidadeRepository;
    private final DesafioEspecialidadeFeitoRepository desafioEspecialidadeFeitoRepository;
    private final InsigniaRepository insigniaRepository;

    public RelatorioService(PessoaRepository pessoaRepository,
                            EspecialidadeRepository especialidadeRepository,
                            DesafioEspecialidadeFeitoRepository desafioEspecialidadeFeitoRepository,
                            InsigniaRepository insigniaRepository) {
        this.pessoaRepository = pessoaRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.desafioEspecialidadeFeitoRepository = desafioEspecialidadeFeitoRepository;
        this.insigniaRepository = insigniaRepository;
    }

    // 1. Dados biográficos de um jovem
    public Pessoa getDadosBiograficos(Long jovemId) {
        return pessoaRepository.findById(jovemId)
                .orElseThrow(() -> new ResourceNotFoundException("Jovem não encontrado"));
    }

    // 2. Jovens com uma especialidade específica
    public List<Pessoa> getJovensPorEspecialidade(Long especialidadeId) {
        Especialidade especialidade = especialidadeRepository.findById(especialidadeId)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));

        return pessoaRepository.findByEspecialidadesContaining(especialidade);
    }

    // 3. Especialidades e insígnias de um jovem
    public JovemProgressaoDTO getEspecialidadesEInsignias(Long jovemId) {
        Pessoa jovem = pessoaRepository.findById(jovemId)
                .orElseThrow(() -> new ResourceNotFoundException("Jovem não encontrado"));

        JovemProgressaoDTO dto = new JovemProgressaoDTO();
        dto.setJovem(jovem);
        dto.setEspecialidades(jovem.getEspecialidades());
        dto.setInsignias(insigniaRepository.findByPessoasContaining(jovem));

        return dto;
    }

    // 4. Requisitos cumpridos por especialidade
    public List<DesafioEspecialidadeFeito> getRequisitosCompletos(Long jovemId, Long especialidadeId) {
        return desafioEspecialidadeFeitoRepository
                .findByPessoaIdAndDesafioEspecialidadeEspecialidadeId(jovemId, especialidadeId);
    }

    // 5. Jovens aptos para Cruzeiro do Sul
    public List<Pessoa> getJovensAptosCruzeiroDoSul() {
        // Implementação específica para verificar requisitos do Cruzeiro do Sul
        return pessoaRepository.findAll().stream()
                .filter(this::verificaRequisitosCruzeiroDoSul)
                .collect(Collectors.toList());
    }

    private boolean verificaRequisitosCruzeiroDoSul(Pessoa jovem) {
        // Lógica para verificar se o jovem completou todos os requisitos
        // do Cruzeiro do Sul (implementação específica)
        return true; // Exemplo simplificado
    }
}