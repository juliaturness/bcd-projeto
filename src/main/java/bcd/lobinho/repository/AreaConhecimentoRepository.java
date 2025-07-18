package bcd.lobinho.repository;

import bcd.lobinho.model.AreaConhecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "area-conhecimento", path = "area-conhecimento")
public interface AreaConhecimentoRepository extends JpaRepository<AreaConhecimento, Integer> {
}