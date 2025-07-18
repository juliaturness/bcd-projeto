package bcd.lobinho.repository;


import bcd.lobinho.model.DadosSaude;
import bcd.lobinho.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(collectionResourceRel = "dados-saude", path = "dados-saude")
public interface DadosSaudeRepository extends JpaRepository<DadosSaude, Integer> {
}
