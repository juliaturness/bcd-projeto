package bcd.lobinho.model;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;


@Data
@Entity
@AllArgsConstructor
public class Vinculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_vinculo;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idResponsavel", nullable = false)
    private Responsavel responsavel;

    protected Vinculo() {}
}