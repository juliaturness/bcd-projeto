package bcd.lobinho.repository;

import bcd.lobinho.model.DesafioInsigniaFeito;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DesafioInsigniaFeitoRepository extends JpaRepository<DesafioInsigniaFeito, DesafioInsigniaFeito.DesafioInsigniaFeitoId> {
    List<DesafioInsigniaFeito> findByPessoaId(Integer idPessoa);
    List<DesafioInsigniaFeito> findByDesafioInsigniaId(Integer idDesafio);
}
