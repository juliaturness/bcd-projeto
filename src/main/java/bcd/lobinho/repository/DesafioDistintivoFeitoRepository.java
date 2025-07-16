package bcd.lobinho.repository;


import bcd.lobinho.model.DesafioDistintivoFeito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DesafioDistintivoFeitoRepository extends JpaRepository<DesafioDistintivoFeito, DesafioDistintivoFeito.DesafioDistintivoFeitoId> {
    List<DesafioDistintivoFeito> findByPessoaId(Integer idPessoa);
    List<DesafioDistintivoFeito> findByDesafioDistintivoId(Integer idDesafio);
}