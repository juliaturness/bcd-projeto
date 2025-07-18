package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "dados_saude")
public class DadosSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDadosSaude;

    @OneToOne
    @JoinColumn(name = "idPessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "idProblema")
    private ProblemasSaude problema;
}
