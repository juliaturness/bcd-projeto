package bcd.lobinho.service;

import bcd.lobinho.exception.ResourceNotFoundException;
import bcd.lobinho.model.Pessoa;
import bcd.lobinho.model.TipoSanguineo;
import bcd.lobinho.repository.PessoaRepository;
import bcd.lobinho.repository.TipoSanguineoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final TipoSanguineoRepository tipoSanguineoRepository;

    public PessoaService(PessoaRepository pessoaRepository,
                         TipoSanguineoRepository tipoSanguineoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.tipoSanguineoRepository = tipoSanguineoRepository;
    }

    @Transactional(readOnly = true)
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorId(Integer id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com id: " + id));
    }

    @Transactional
    public Pessoa criar(Pessoa pessoa) {
        // Validar CPF único
        if (pessoaRepository.findByCpf(pessoa.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        // Se tiver tipo sanguíneo, verificar existência e associar
        if (pessoa.getTipoSanguineo() != null && pessoa.getTipoSanguineo().getId() != null) {
            TipoSanguineo tipo = tipoSanguineoRepository.findById(pessoa.getTipoSanguineo().getId()).orElseThrow(() -> new ResourceNotFoundException("Tipo sanguíneo não encontrado"));
            pessoa.setTipoSanguineo(tipo);
        } else {
            pessoa.setTipoSanguineo(null);
        }

        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public Pessoa atualizar(Integer id, Pessoa pessoaAtualizada) {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com id: " + id));

        // Validar CPF se alterado
        if (!pessoaExistente.getCpf().equals(pessoaAtualizada.getCpf())) {
            if (pessoaRepository.findByCpf(pessoaAtualizada.getCpf()).isPresent()) {
                throw new IllegalArgumentException("CPF já cadastrado para outra pessoa.");
            }
        }

        pessoaExistente.setNome(pessoaAtualizada.getNome());
        pessoaExistente.setCpf(pessoaAtualizada.getCpf());
        pessoaExistente.setEndereco(pessoaAtualizada.getEndereco());
        pessoaExistente.setTelefone(pessoaAtualizada.getTelefone());
        pessoaExistente.setDataNascimento(pessoaAtualizada.getDataNascimento());
        pessoaExistente.setGenero(pessoaAtualizada.getGenero());

        // Atualizar tipo sanguíneo
        if (pessoaAtualizada.getTipoSanguineo() != null && pessoaAtualizada.getTipoSanguineo().getId() != null) {
            TipoSanguineo tipo = tipoSanguineoRepository.findById(pessoaAtualizada.getTipoSanguineo().getId()).orElseThrow(() -> new ResourceNotFoundException("Tipo sanguíneo não encontrado"));
            pessoaExistente.setTipoSanguineo(tipo);
        } else {
            pessoaExistente.setTipoSanguineo(null);
        }

        return pessoaRepository.save(pessoaExistente);
    }

    @Transactional
    public void excluir(Integer id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com id: " + id));
        pessoaRepository.delete(pessoa);
    }

    @Transactional
    public Pessoa salvar(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
        return pessoa;
    }

    @Transactional(readOnly = true)
    public Pessoa buscarPorCpf(String cpf) {
        return pessoaRepository.findByCpf(cpf).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada com CPF: " + cpf));
    }

    @Transactional(readOnly = true)
    public List<Pessoa> buscarPorNome(String nome) {
        return pessoaRepository.findByNomeContainingIgnoreCase(nome);
    }
}
