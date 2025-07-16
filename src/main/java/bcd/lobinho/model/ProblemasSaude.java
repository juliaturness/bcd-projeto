package bcd.lobinho.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "problema_saude")
public class ProblemasSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tipo_problema")
    private String tipoProblema;

    private String descricao;

    @OneToMany(mappedBy = "problemaSaude")
    private List<DadosSaude> dadosSaude;
}