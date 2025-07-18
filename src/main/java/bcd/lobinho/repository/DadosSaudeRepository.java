package bcd.lobinho.repository;


import bcd.lobinho.model.DadosSaude;
import bcd.lobinho.model.DesafioDistintivoFeito;
import bcd.lobinho.model.Pessoa;
import org.hibernate.sql.ast.tree.expression.Over;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "dados-saude", path = "dados-saude")
public interface DadosSaudeRepository extends JpaRepository<DadosSaude, Integer> {
    Optional<DadosSaude> findByPessoa(Pessoa pessoa);

}
