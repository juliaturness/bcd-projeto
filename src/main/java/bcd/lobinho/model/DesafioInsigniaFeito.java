package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@Table(name = "DesafioInsigniaFeito")
public class DesafioInsigniaFeito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDesafioInsigniaFeito;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "idDesafioInsignia", nullable = false)
    private DesafioInsignia desafioInsignia;

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    private Pessoa pessoa;

    protected DesafioInsigniaFeito() {}

}