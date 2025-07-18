package bcd.lobinho.repository;

import bcd.lobinho.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "responsavel", path = "responsavel")
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

}