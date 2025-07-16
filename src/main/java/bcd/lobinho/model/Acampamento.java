package bcd.lobinho.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "acampamento")
public class Acampamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_acampamento")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false)
    private LocalDate data;

    @OneToMany(mappedBy = "acampamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NoiteAcampada> participantes;
}