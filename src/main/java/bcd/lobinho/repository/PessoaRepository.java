package bcd.lobinho.repository;

import bcd.lobinho.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
    // Buscar pessoa por CPF
    Optional<Pessoa> findByCpf(String cpf);

    // Buscar pessoas por nome
    List<Pessoa> findByNomeContainingIgnoreCase(String nome);

    // Buscar pessoas pelo tipo sanguíneo (por id do tipo sanguíneo)
    @Query("SELECT p FROM Pessoa p WHERE p.tipoSanguineo.id = :tipoId")
    List<Pessoa> findByTipoSanguineoId(@Param("tipoId") Integer tipoId);

    // Contar pessoas por gênero
    @Query("SELECT COUNT(p) FROM Pessoa p WHERE p.genero = :genero")
    long countByGenero(@Param("genero") String genero);

}
