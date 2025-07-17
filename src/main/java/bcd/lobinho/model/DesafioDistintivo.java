package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "desafio_distintivo")
public class DesafioDistintivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_distintivo")
    private Distintivo distintivo;

}
