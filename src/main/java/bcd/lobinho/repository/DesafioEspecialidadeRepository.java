package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioEspecialidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DesafioEspecialidadeRepository extends JpaRepository<DesafioEspecialidade, Integer> {
    List<DesafioEspecialidade> findByEspecialidadeId(Integer idEspecialidade);
}