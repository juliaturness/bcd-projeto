package bcd.lobinho.repository;


import bcd.lobinho.model.NoiteAcampada;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoiteAcampadaRepository extends JpaRepository<NoiteAcampada, NoiteAcampada.NoiteAcampadaId> {
    List<NoiteAcampada> findByPessoaId(Integer idPessoa);
    List<NoiteAcampada> findByAcampamentoId(Integer idAcampamento);
}
