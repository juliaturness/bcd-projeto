package bcd.lobinho.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class ProblemasSaude implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProblema;

    @Column(nullable = false)
    private String tipoProblema;

    private String descricao;

    @OneToMany(mappedBy = "problema")
    private List<DadosSaude> dadosSaude;

    protected ProblemasSaude() {}

}