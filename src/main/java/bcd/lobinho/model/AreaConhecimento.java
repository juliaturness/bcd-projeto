package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "AreaConhecimento" )
@AllArgsConstructor
public class AreaConhecimento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAreaConhecimento;

    @Column(nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "areaConhecimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Especialidade> especialidades;

    protected AreaConhecimento() {}


}