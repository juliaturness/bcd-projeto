package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@Table(name = "DesafioEspecialidadeFeito" )
public class DesafioEspecialidadeFeito implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDesafioEspecialidadeFeito;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "idDesafioEspecialidade", nullable = false)
    private DesafioEspecialidade desafioEspecialidade;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;

    protected DesafioEspecialidadeFeito() {}
}