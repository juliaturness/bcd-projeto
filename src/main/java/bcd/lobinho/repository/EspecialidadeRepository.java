package bcd.lobinho.repository;

import bcd.lobinho.model.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EspecialidadeRepository extends JpaRepository<Especialidade, Integer> {
    List<Especialidade> findByNomeContainingIgnoreCase(String nome);
    List<Especialidade> findByAreaConhecimentoId(Integer idArea);
}
