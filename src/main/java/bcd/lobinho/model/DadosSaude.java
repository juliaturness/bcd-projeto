package bcd.lobinho.model;

import jakarta.persistence.*;

@Entity
@Table(name = "dados_saude")
public class DadosSaude {
    @Id
    @OneToOne
    @JoinColumn(name = "id_pessoa")
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "id_problema_saude")
    private ProblemasSaude problemaSaude;
}
