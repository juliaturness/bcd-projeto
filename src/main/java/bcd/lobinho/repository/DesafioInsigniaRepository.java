package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioInsignia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "desafios-insignia", path = "desafios-insignia")
public interface DesafioInsigniaRepository extends JpaRepository<DesafioInsignia, Integer> {
}
