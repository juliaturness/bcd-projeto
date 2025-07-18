package bcd.lobinho.repository;

import bcd.lobinho.model.Pessoa;
import bcd.lobinho.model.TipoSanguineo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "pessoa", path = "pessoa")
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    List<Pessoa> findByTipoSanguineo(TipoSanguineo tipoSanguineo);

    // Novo método para buscar jovem por CPF
    Optional<Pessoa> findByCpf(String cpf);

}
