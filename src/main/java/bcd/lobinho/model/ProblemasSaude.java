package bcd.lobinho.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Table(name = "ProblemasSaude" )
public class ProblemasSaude implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProblema;

    @Column(nullable = false)
    private String tipoProblema;

    private String descricao;

    @OneToMany(mappedBy = "problema", cascade = CascadeType.ALL)
    private List<DadosSaude> dadosSaude;

    protected ProblemasSaude() {}

}