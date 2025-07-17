package bcd.lobinho.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.AreaConhecimento;
import bcd.lobinho.repository.AreaConhecimentoRepository;
import java.util.List;

@Service
public class AreaConhecimentoService {
    private final AreaConhecimentoRepository areaConhecimentoRepository;

    public AreaConhecimentoService(AreaConhecimentoRepository areaConhecimentoRepository) {
        this.areaConhecimentoRepository = areaConhecimentoRepository;
    }

    @Transactional(readOnly = true)
    public List<AreaConhecimento> listarTodos() {
        return areaConhecimentoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public AreaConhecimento buscarPorId(Integer id) {
        return areaConhecimentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Área de conhecimento não encontrada com id: " + id));
    }

    @Transactional
    public AreaConhecimento criar(AreaConhecimento areaConhecimento) {
        return areaConhecimentoRepository.save(areaConhecimento);
    }

    @Transactional
    public AreaConhecimento atualizar(Integer id, AreaConhecimento areaAtualizada) {
        AreaConhecimento area = areaConhecimentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Área de conhecimento não encontrada"));
        area.setNome(areaAtualizada.getNome());
        return areaConhecimentoRepository.save(area);
    }

    @Transactional
    public void excluir(Integer id) {
        AreaConhecimento area = areaConhecimentoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Área de conhecimento não encontrada"));
        areaConhecimentoRepository.delete(area);
    }
}