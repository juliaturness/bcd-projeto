package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "DesafioEspecialidade" )
public class DesafioEspecialidade implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDesafioEspecialidade;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idEspecialidade", nullable = false)
    private Especialidade especialidade;

    @OneToMany(mappedBy = "desafioEspecialidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesafioEspecialidadeFeito> desafiofeitos;

    protected DesafioEspecialidade() {}
}
