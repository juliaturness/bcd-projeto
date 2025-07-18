package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
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

    public class DadosSaudeId implements Serializable {
        private Long pessoa;
        private Long problemaSaude;

        // construtores, equals e hashCode
    }
}
