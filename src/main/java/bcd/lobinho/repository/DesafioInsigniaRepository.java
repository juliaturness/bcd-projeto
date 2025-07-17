package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioInsignia;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DesafioInsigniaRepository extends JpaRepository<DesafioInsignia, Integer> {
    List<DesafioInsignia> findByInsigniaId(Integer idInsignia);
}
