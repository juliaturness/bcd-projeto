package bcd.lobinho.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.Especialidade;
import bcd.lobinho.repository.EspecialidadeRepository;
import java.util.List;

@Service
public class EspecialidadeService {
    private final EspecialidadeRepository especialidadeRepository;

    public EspecialidadeService(EspecialidadeRepository especialidadeRepository) {
        this.especialidadeRepository = especialidadeRepository;
    }

    @Transactional(readOnly = true)
    public List<Especialidade> listarTodos() {
        return especialidadeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Especialidade buscarPorId(Integer id) {
        return especialidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
    }

    @Transactional
    public Especialidade criar(Especialidade especialidade) {
        return especialidadeRepository.save(especialidade);
    }

    @Transactional
    public Especialidade atualizar(Integer id, Especialidade especialidadeAtualizada) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));

        especialidade.setNome(especialidadeAtualizada.getNome());
        return especialidadeRepository.save(especialidade);
    }

    @Transactional
    public void excluir(Integer id) {
        Especialidade especialidade = especialidadeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Especialidade não encontrada"));
        especialidadeRepository.delete(especialidade);
    }
}