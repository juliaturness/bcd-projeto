package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "responsavel")
public class Responsavel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_responsavel")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 100)
    private String email;

    @Column(length = 20, nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "responsavel")
    private List<Vinculo> vinculos;
}