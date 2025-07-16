package bcd.lobinho.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "desafio_distintivo")
public class DesafioDistintivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_distintivo")
    private Distintivo distintivo;

    @OneToMany(mappedBy = "desafioDistintivo")
    private List<DesafioDistintivoFeito> realizacoes;
}
