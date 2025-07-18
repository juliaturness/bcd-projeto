package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioEspecialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "desafios-especialidade", path = "desafios-especialidade")
public interface DesafioEspecialidadeRepository extends JpaRepository<DesafioEspecialidade, Integer> {
}