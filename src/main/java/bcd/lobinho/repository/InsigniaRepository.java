package bcd.lobinho.repository;

import bcd.lobinho.model.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "insignia", path = "insignia")
public interface InsigniaRepository extends JpaRepository<Insignia, Integer> {

}
