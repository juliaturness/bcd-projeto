package bcd.lobinho.model;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "DesafioInsignia")
@AllArgsConstructor
public class DesafioInsignia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDesafioInsignia;

    @Column(nullable = false, length = 100)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "idInsignia", nullable = false)
    private Insignia insignia;

    @OneToMany(mappedBy = "desafioInsignia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesafioInsigniaFeito> realizacoes;

    protected DesafioInsignia() {}
}