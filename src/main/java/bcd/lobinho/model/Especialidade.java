package bcd.lobinho.model;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "especialidade")
public class Especialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidade")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_area_conhecimento", nullable = false)
    private AreaConhecimento areaConhecimento;

    @OneToMany(mappedBy = "especialidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesafioEspecialidade> desafios;
}