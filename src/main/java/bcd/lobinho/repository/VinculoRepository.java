package bcd.lobinho.repository;

import bcd.lobinho.model.Vinculo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VinculoRepository extends JpaRepository<Vinculo, Vinculo.VinculoId> {
    List<Vinculo> findByPessoaId(Integer idPessoa);
    List<Vinculo> findByResponsavelId(Integer idResponsavel);
}