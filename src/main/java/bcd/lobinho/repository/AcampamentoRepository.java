package bcd.lobinho.repository;

import bcd.lobinho.model.Acampamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "acampamentos", path = "acampamentos")
public interface AcampamentoRepository extends JpaRepository<Acampamento, Integer> {

}