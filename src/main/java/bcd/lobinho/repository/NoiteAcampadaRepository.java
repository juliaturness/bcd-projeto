package bcd.lobinho.repository;


import bcd.lobinho.model.NoiteAcampada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "noite-acampada", path = "noite-acampada")
public interface NoiteAcampadaRepository extends JpaRepository<NoiteAcampada, Integer> {
}
