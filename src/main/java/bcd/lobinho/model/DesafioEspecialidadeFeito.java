package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "desafio_especialidade_feito")
public class DesafioEspecialidadeFeito {
    @EmbeddedId
    private DesafioEspecialidadeFeitoId id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @MapsId("idDesafioEspecialidade")
    @JoinColumn(name = "id_desafio_especialidade", nullable = false)
    private DesafioEspecialidade desafioEspecialidade;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;
}

@Embeddable
class DesafioEspecialidadeFeitoId implements Serializable {
    @Column(name = "id_desafio_especialidade")
    private Integer idDesafioEspecialidade;

    @Column(name = "id_pessoa")
    private Integer idPessoa;
}