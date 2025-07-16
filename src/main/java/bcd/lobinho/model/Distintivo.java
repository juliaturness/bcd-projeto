package bcd.lobinho.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "distintivo")
public class Distintivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @OneToMany(mappedBy = "distintivo")
    private List<DesafioDistintivo> desafios;
}