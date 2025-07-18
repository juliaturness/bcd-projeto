package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
public class NoiteAcampada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNoiteAcampada;

    @ManyToOne
    @JoinColumn(name = "idAcampamento", nullable = false)
    private Acampamento acampamento;

    @ManyToOne
    @JoinColumn(name = "id", nullable = false)
    private Pessoa pessoa;

    protected NoiteAcampada() {}

}
