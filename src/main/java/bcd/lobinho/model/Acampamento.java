package bcd.lobinho.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "Acampamento" )
@AllArgsConstructor
public class Acampamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAcampamento;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private LocalDate data;

    @OneToMany(mappedBy = "acampamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoiteAcampada> noitesAcampadas;

    protected Acampamento() {}

}