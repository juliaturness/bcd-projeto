package bcd.lobinho.repository;


import bcd.lobinho.model.DadosSaude;
import bcd.lobinho.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosSaudeRepository extends JpaRepository<DadosSaude, Integer> {
}
