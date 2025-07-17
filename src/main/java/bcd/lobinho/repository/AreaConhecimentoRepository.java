package bcd.lobinho.repository;

import bcd.lobinho.model.AreaConhecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface AreaConhecimentoRepository extends JpaRepository<AreaConhecimento, Integer> {
    List<AreaConhecimento> findByNomeContainingIgnoreCase(String nome);
}