package bcd.lobinho.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "desafio_insignia")
public class DesafioInsignia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_desafio_insignia")
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_insignia", nullable = false)
    private Insignia insignia;

    @OneToMany(mappedBy = "desafioInsignia")
    private List<DesafioInsigniaFeito> realizacoes;
}