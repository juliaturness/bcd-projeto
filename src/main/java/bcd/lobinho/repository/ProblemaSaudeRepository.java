package bcd.lobinho.repository;

import bcd.lobinho.model.ProblemasSaude;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProblemaSaudeRepository extends JpaRepository<ProblemasSaude, Integer> {
    List<ProblemasSaude> findByTipoProblemaContainingIgnoreCase(String tipo);
}
