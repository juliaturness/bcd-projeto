package bcd.lobinho.repository;

import bcd.lobinho.model.Distintivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DistintivoRepository extends JpaRepository<Distintivo, Integer> {
    List<Distintivo> findByNomeContainingIgnoreCase(String nome);
}
