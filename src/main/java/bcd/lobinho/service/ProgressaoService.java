package bcd.lobinho.service;

import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.*;
import bcd.lobinho.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProgressaoService {

    private final DesafioDistintivoFeitoRepository desafioDistintivoFeitoRepository;
    private final DesafioInsigniaFeitoRepository desafioInsigniaFeitoRepository;
    private final DesafioEspecialidadeFeitoRepository desafioEspecialidadeFeitoRepository;
    private final PessoaRepository pessoaRepository;
    private final DesafioDistintivoRepository desafioDistintivoRepository;
    private final DesafioInsigniaRepository desafioInsigniaRepository;
    private final DesafioEspecialidadeRepository desafioEspecialidadeRepository;

    public ProgressaoService(DesafioDistintivoFeitoRepository desafioDistintivoFeitoRepository,
                             DesafioInsigniaFeitoRepository desafioInsigniaFeitoRepository,
                             DesafioEspecialidadeFeitoRepository desafioEspecialidadeFeitoRepository,
                             PessoaRepository pessoaRepository,
                             DesafioDistintivoRepository desafioDistintivoRepository,
                             DesafioInsigniaRepository desafioInsigniaRepository,
                             DesafioEspecialidadeRepository desafioEspecialidadeRepository) {
        this.desafioDistintivoFeitoRepository = desafioDistintivoFeitoRepository;
        this.desafioInsigniaFeitoRepository = desafioInsigniaFeitoRepository;
        this.desafioEspecialidadeFeitoRepository = desafioEspecialidadeFeitoRepository;
        this.pessoaRepository = pessoaRepository;
        this.desafioDistintivoRepository = desafioDistintivoRepository;
        this.desafioInsigniaRepository = desafioInsigniaRepository;
        this.desafioEspecialidadeRepository = desafioEspecialidadeRepository;
    }

    @Transactional
    public DesafioDistintivoFeito registrarDesafioDistintivo(Integer idPessoa, Integer idDesafio, DesafioDistintivoFeito desafioFeito) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        DesafioDistintivo desafio = desafioDistintivoRepository.findById(idDesafio)
                .orElseThrow(() -> new ResourceNotFoundException("Desafio distintivo não encontrado"));

        desafioFeito.setPessoa(pessoa);
        desafioFeito.setDesafioDistintivo(desafio);

        return desafioDistintivoFeitoRepository.save(desafioFeito);
    }

    @Transactional(readOnly = true)
    public List<DesafioDistintivoFeito> listarDesafiosDistintivosPorPessoa(Integer idPessoa) {
        return desafioDistintivoFeitoRepository.findByPessoaId(idPessoa);
    }

    @Transactional
    public DesafioInsigniaFeito registrarDesafioInsignia(Integer idPessoa, Integer idDesafio, DesafioInsigniaFeito desafioFeito) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        DesafioInsignia desafio = desafioInsigniaRepository.findById(idDesafio)
                .orElseThrow(() -> new ResourceNotFoundException("Desafio insígnia não encontrado"));

        desafioFeito.setPessoa(pessoa);
        desafioFeito.setDesafioInsignia(desafio);

        return desafioInsigniaFeitoRepository.save(desafioFeito);
    }

    @Transactional(readOnly = true)
    public List<DesafioInsigniaFeito> listarDesafiosInsigniasPorPessoa(Integer idPessoa) {
        return desafioInsigniaFeitoRepository.findByPessoaId(idPessoa);
    }

    @Transactional
    public DesafioEspecialidadeFeito registrarDesafioEspecialidade(Integer idPessoa, Integer idDesafio, DesafioEspecialidadeFeito desafioFeito) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        DesafioEspecialidade desafio = desafioEspecialidadeRepository.findById(idDesafio)
                .orElseThrow(() -> new ResourceNotFoundException("Desafio especialidade não encontrado"));

        desafioFeito.setPessoa(pessoa);
        desafioFeito.setDesafioEspecialidade(desafio);

        return desafioEspecialidadeFeitoRepository.save(desafioFeito);
    }

    @Transactional(readOnly = true)
    public List<DesafioEspecialidadeFeito> listarDesafiosEspecialidadesPorPessoa(Integer idPessoa) {
        return desafioEspecialidadeFeitoRepository.findByPessoaId(idPessoa);
    }

    @Transactional(readOnly = true)
    public boolean verificarProgressaoLobinho(Integer idPessoa) {
        // Implementação das regras de progressão no ramo Lobinho
        List<DesafioDistintivoFeito> distintivos = listarDesafiosDistintivosPorPessoa(idPessoa);
        List<DesafioInsigniaFeito> insignias = listarDesafiosInsigniasPorPessoa(idPessoa);
        List<DesafioEspecialidadeFeito> especialidades = listarDesafiosEspecialidadesPorPessoa(idPessoa);

        // Verifica se completou todos os distintivos necessários
        boolean distintivosCompletos = distintivos.stream()
                .filter(d -> d.getDataFim() != null)
                .count() >= 5; // Exemplo: precisa de 5 distintivos

        // Verifica se completou pelo menos 1 insígnia
        boolean insigniasCompletas = insignias.stream()
                .anyMatch(i -> i.getData() != null);

        // Verifica se completou pelo menos 3 especialidades
        boolean especialidadesCompletas = especialidades.stream()
                .filter(e -> e.getData() != null)
                .count() >= 3;

        return distintivosCompletos && insigniasCompletas && especialidadesCompletas;
    }
}
