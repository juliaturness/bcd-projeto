package bcd.lobinho.repository;

import bcd.lobinho.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);
    Pessoa findByCpf(String cpf);
    List<Pessoa> findByDataNascimentoBetween(LocalDate inicio, LocalDate fim);
}
