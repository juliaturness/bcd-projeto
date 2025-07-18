package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioDistintivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "desafios-distintivo", path = "desafios-distintivo")
public interface DesafioDistintivoRepository extends JpaRepository<DesafioDistintivo, Integer> {

}
