package bcd.lobinho.service;

import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.Pessoa;
import bcd.lobinho.model.Responsavel;
import bcd.lobinho.model.TipoSanguineo;
import bcd.lobinho.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ResponsavelService {
    private final ResponsavelRepository responsavelRepository;

    public ResponsavelService(ResponsavelRepository responsavelRepository) {
        this.responsavelRepository = responsavelRepository;
    }

    public List<Responsavel> listarTodos() {
        return responsavelRepository.findAll();
    }

    public Responsavel buscarPorId(Integer id) {
        return responsavelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado com id: " + id));
    }

    public Responsavel salvar(Responsavel responsavel) {
        return responsavelRepository.save(responsavel);
    }

    public void excluir(Integer id) {
        responsavelRepository.deleteById(id);
    }

    @Transactional
    public Responsavel atualizar(Integer id, Responsavel responsavelAtualizado) {
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado"));

        responsavel.setNome(responsavelAtualizado.getNome());
        responsavel.setEmail(responsavelAtualizado.getEmail());
        responsavel.setTelefone(responsavelAtualizado.getTelefone());

        return responsavelRepository.save(responsavel);
    }

    public List<Responsavel> buscarPorPessoa(Integer idPessoa) {
        return responsavelRepository.findByPessoaId(idPessoa);
    }

    public Responsavel criar(Responsavel pessoa) {
        return responsavelRepository.save(pessoa);
    }



}
