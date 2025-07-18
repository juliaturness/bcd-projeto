package bcd.lobinho.repository;

import bcd.lobinho.model.Distintivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "distintivo", path = "distintivo")
public interface DistintivoRepository extends JpaRepository<Distintivo, Integer> {

}
