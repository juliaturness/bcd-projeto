package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "desafio_especialidade")
public class DesafioEspecialidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desafio_especialidade")
    private Integer id;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_especialidade", nullable = false)
    private Especialidade especialidade;

    @OneToMany(mappedBy = "desafioEspecialidade")
    private List<DesafioEspecialidadeFeito> realizacoes;
}
