package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Entity
@Table(name = "noite_acampada")
public class NoiteAcampada {
    @EmbeddedId
    private NoiteAcampadaId id;

    @ManyToOne
    @MapsId("id_acampamento")
    @JoinColumn(name = "id_acampamento", nullable = false)
    private Acampamento acampamento;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;
}


@Embeddable
class NoiteAcampadaId implements Serializable {
    @Column(name = "id_acampamento")
    private Integer idAcampamento;

    @Column(name = "id_pessoa")
    private Integer idPessoa;
}
