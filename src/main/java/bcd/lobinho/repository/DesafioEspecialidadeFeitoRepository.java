package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioEspecialidadeFeito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DesafioEspecialidadeFeitoRepository extends JpaRepository<DesafioEspecialidadeFeito, DesafioEspecialidadeFeito.DesafioEspecialidadeFeitoId> {
    List<DesafioEspecialidadeFeito> findByPessoaId(Integer idPessoa);
    List<DesafioEspecialidadeFeito> findByDesafioEspecialidadeId(Integer idDesafio);
}