package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioDistintivoFeito;
import bcd.lobinho.model.DesafioEspecialidadeFeito;
import bcd.lobinho.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "desafios-especialidade-feito", path = "desafios-especialidade-feito")
public interface DesafioEspecialidadeFeitoRepository extends JpaRepository<DesafioEspecialidadeFeito, Integer> {
    List<DesafioEspecialidadeFeito> findByPessoa(Pessoa pessoa);

}