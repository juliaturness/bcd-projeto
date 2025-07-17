package bcd.lobinho.service;

import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.DadosSaude;
import bcd.lobinho.model.Pessoa;
import bcd.lobinho.model.TipoSanguineo;
import bcd.lobinho.repository.DadosSaudeRepository;
import bcd.lobinho.repository.PessoaRepository;
import bcd.lobinho.repository.TipoSanguineoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final TipoSanguineoRepository tipoSanguineoRepository;
    private final DadosSaudeRepository dadoSaudeRepository;

    public PessoaService(PessoaRepository pessoaRepository,
                         TipoSanguineoRepository tipoSanguineoRepository,
                         DadosSaudeRepository dadoSaudeRepository) {
        this.pessoaRepository = pessoaRepository;
        this.tipoSanguineoRepository = tipoSanguineoRepository;
        this.dadoSaudeRepository = dadoSaudeRepository;
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorId(Integer id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com id: " + id));
    }

    @Transactional
    public Pessoa criar(Pessoa pessoa) {
        if(pessoa.getTipoSanguineo() != null && pessoa.getTipoSanguineo().getId() != null) {
            TipoSanguineo tipoSanguineo = tipoSanguineoRepository.findById(pessoa.getTipoSanguineo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo sanguíneo não encontrado"));
            pessoa.setTipoSanguineo(tipoSanguineo);
        }
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa atualizar(Integer id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com id: " + id));

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setTelefone(pessoaAtualizada.getTelefone());
        pessoaExistente.setCpf(pessoaAtualizada.getCpf());

        if(pessoaAtualizada.getTipoSanguineo() != null) {
            TipoSanguineo tipoSanguineo = tipoSanguineoRepository.findById(pessoaAtualizada.getTipoSanguineo().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Tipo sanguíneo não encontrado"));
            pessoaExistente.setTipoSanguineo(tipoSanguineo);
        }

        return pessoaRepository.save(pessoaExistente);
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Integer id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com id: " + id));
        pessoaRepository.delete(pessoa);
    }

    @Transactional
    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf);
    }

    @Transactional(readOnly = true)
    public List<Pessoa> buscarPorNome(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public DadosSaude adicionarDadoSaude(Integer idPessoa, DadosSaude dadoSaude) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));

        dadoSaude.setPessoa(pessoa);
        return dadoSaudeRepository.save(dadoSaude);
    }
}