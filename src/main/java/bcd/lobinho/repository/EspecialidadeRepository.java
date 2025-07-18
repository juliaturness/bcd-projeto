package bcd.lobinho.repository;

import bcd.lobinho.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "especialidade", path = "especialidade")
public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {

}
