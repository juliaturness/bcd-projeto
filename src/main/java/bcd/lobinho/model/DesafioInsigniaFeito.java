package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "desafio_insignia_feito")
public class DesafioInsigniaFeito {
    @EmbeddedId
    private DesafioInsigniaFeitoId id;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @MapsId("id_desafio_insignia")
    @JoinColumn(name = "id_desafio_insignia", nullable = false)
    private DesafioInsignia desafioInsignia;

    @ManyToOne
    @MapsId("idPessoa")
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;


    @NoArgsConstructor
    public class DesafioInsigniaFeitoId implements Serializable {
        @Column(name = "id_desafio_insignia")
        private Integer idDesafioInsignia;

        @Column(name = "id_pessoa")
        private Integer idPessoa;
    }
}