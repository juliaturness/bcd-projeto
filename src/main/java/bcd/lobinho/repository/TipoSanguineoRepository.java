package bcd.lobinho.repository;


import bcd.lobinho.model.TipoSanguineo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "tipo-sanguineo", path = "tipo-sanguineo")
public interface TipoSanguineoRepository extends JpaRepository<TipoSanguineo, Integer> {
}
