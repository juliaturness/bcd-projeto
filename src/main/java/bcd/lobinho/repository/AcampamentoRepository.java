package bcd.lobinho.repository;

import bcd.lobinho.model.Acampamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface AcampamentoRepository extends JpaRepository<Acampamento, Integer> {
    List<Acampamento> findByNomeContainingIgnoreCase(String nome);
    List<Acampamento> findByDataBetween(LocalDate inicio, LocalDate fim);
}