package bcd.lobinho.service;

import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.Distintivo;
import bcd.lobinho.repository.DistintivoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DistintivoService {
    private final DistintivoRepository distintivoRepository;

    public DistintivoService(DistintivoRepository distintivoRepository) {
        this.distintivoRepository = distintivoRepository;
    }

    public List<Distintivo> listarTodos() {
        return distintivoRepository.findAll();
    }

    public Distintivo buscarPorId(Integer id) {
        return distintivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Distintivo não encontrado com id: " + id));
    }

    public Distintivo salvar(Distintivo distintivo) {
        return distintivoRepository.save(distintivo);
    }

    public void excluir(Integer id) {
        distintivoRepository.deleteById(id);
    }

    public List<Distintivo> buscarPorNome(String nome) {
        return distintivoRepository.findByNomeContainingIgnoreCase(nome);
    }
}
