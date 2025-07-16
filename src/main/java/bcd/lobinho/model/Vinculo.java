package bcd.lobinho.model;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name = "vinculo")
public class Vinculo {
    @EmbeddedId
    private VinculoId id;

    @OneToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @MapsId("idResponsavel")
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;


    public class VinculoId implements Serializable {
        @Column(name = "id_pessoa")
        private Integer idPessoa;

        @Column(name = "id_responsavel")
        private Integer idResponsavel;
    }
}