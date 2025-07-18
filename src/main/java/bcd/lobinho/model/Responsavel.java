package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@Table(name = "Responsavel" )
public class Responsavel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idResponsavel;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pessoa pessoa;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "responsavel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vinculo> vinculos;

    protected Responsavel() {}
}