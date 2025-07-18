package bcd.lobinho.repository;


import bcd.lobinho.model.DesafioDistintivoFeito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "desafios-distintivo-feito", path = "desafios-distintivo-feito")
public interface DesafioDistintivoFeitoRepository extends JpaRepository<DesafioDistintivoFeito, Integer> {

}
