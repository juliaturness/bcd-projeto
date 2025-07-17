package bcd.lobinho.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "vinculo")
public class Vinculo {
    @EmbeddedId
    private VinculoId id;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @MapsId("idResponsavel")
    @JoinColumn(name = "id_responsavel")
    private Responsavel responsavel;

    @Embeddable
    public static class VinculoId implements Serializable {
        @Column(name = "id_pessoa")
        private Integer idPessoa;

        @Column(name = "id_responsavel")
        private Integer idResponsavel;
    }
}