package bcd.lobinho.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "desafio_distintivo_feito")
public class DesafioDistintivoFeito {
    @EmbeddedId
    private DesafioDistintivoFeitoId id;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @ManyToOne
    @MapsId("idDesafioDistintivo")
    @JoinColumn(name = "id_desafio_distintivo")
    private DesafioDistintivo desafioDistintivo;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;


    @Embeddable
    public class DesafioDistintivoFeitoId implements Serializable {
        private Integer idDesafioDistintivo;
        private Integer idPessoa;
    }
}