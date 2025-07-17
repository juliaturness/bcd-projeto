package bcd.lobinho.repository;

import bcd.lobinho.model.Insignia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface InsigniaRepository extends JpaRepository<Insignia, Integer> {
    List<Insignia> findByNomeContainingIgnoreCase(String nome);
}
