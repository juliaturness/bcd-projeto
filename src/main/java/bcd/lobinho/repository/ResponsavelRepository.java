package bcd.lobinho.repository;

import bcd.lobinho.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
    List<Responsavel> findByPessoaId(Integer idPessoa);
}