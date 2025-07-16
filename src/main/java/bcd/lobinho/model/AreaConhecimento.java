package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "area_conhecimento")
public class AreaConhecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area_conhecimento")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "areaConhecimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Especialidade> especialidades;
}