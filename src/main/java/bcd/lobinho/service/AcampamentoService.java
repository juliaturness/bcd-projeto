package bcd.lobinho.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.Acampamento;
import bcd.lobinho.repository.AcampamentoRepository;
import java.util.List;

@Service
public class AcampamentoService {
    private final AcampamentoRepository acampamentoRepository;

    public AcampamentoService(AcampamentoRepository acampamentoRepository) {
        this.acampamentoRepository = acampamentoRepository;
    }

    @Transactional(readOnly = true)
    public List<Acampamento> listarTodos() {
        return acampamentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Acampamento buscarPorId(Integer id) {
        return acampamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acampamento não encontrado"));
    }

    @Transactional
    public Acampamento criar(Acampamento acampamento) {
        return acampamentoRepository.save(acampamento);
    }

    @Transactional
    public Acampamento atualizar(Integer id, Acampamento acampamentoAtualizado) {
        Acampamento acampamento = acampamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acampamento não encontrado"));

        acampamento.setNome(acampamentoAtualizado.getNome());
        acampamento.setData(acampamentoAtualizado.getData());

        return acampamentoRepository.save(acampamento);
    }

    @Transactional
    public void excluir(Integer id) {
        Acampamento acampamento = acampamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Acampamento não encontrado"));
        acampamentoRepository.delete(acampamento);
    }
}