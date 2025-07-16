package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioDistintivo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DesafioDistintivoRepository extends JpaRepository<DesafioDistintivo, Integer> {
    List<DesafioDistintivo> findByDistintivoId(Integer idDistintivo);
}
