package bcd.lobinho.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.Insignia;
import bcd.lobinho.repository.InsigniaRepository;
import java.util.List;

@Service
public class InsigniaService {
    private final InsigniaRepository insigniaRepository;

    public InsigniaService(InsigniaRepository insigniaRepository) {
        this.insigniaRepository = insigniaRepository;
    }

    @Transactional(readOnly = true)
    public List<Insignia> listarTodos() {
        return insigniaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Insignia buscarPorId(Integer id) {
        return insigniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insígnia não encontrada"));
    }

    @Transactional
    public Insignia criar(Insignia insignia) {
        return insigniaRepository.save(insignia);
    }

    @Transactional
    public Insignia atualizar(Integer id, Insignia insigniaAtualizada) {
        Insignia insignia = insigniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insígnia não encontrada"));

        insignia.setNome(insigniaAtualizada.getNome());
        return insigniaRepository.save(insignia);
    }

    @Transactional
    public void excluir(Integer id) {
        Insignia insignia = insigniaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insígnia não encontrada"));
        insigniaRepository.delete(insignia);
    }
}