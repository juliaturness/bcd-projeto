package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioDistintivoFeito;
import bcd.lobinho.model.DesafioInsigniaFeito;
import bcd.lobinho.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "desafios-insignia-feito", path = "desafios-insignia-feito")
public interface DesafioInsigniaFeitoRepository extends JpaRepository<DesafioInsigniaFeito, Integer> {
    List<DesafioInsigniaFeito> findByPessoa(Pessoa pessoa);

}
