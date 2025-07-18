package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioDistintivoFeito;
import bcd.lobinho.model.Pessoa;
import bcd.lobinho.model.Vinculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "vinculo", path = "vinculo")
public interface VinculoRepository extends JpaRepository<Vinculo, Integer> {
    List<Vinculo> findByPessoa(Pessoa pessoa);

}