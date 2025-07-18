package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
public class DesafioDistintivo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDesafioDistintivo;

    @Column(nullable = false, length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "idDistintivo")
    private Distintivo distintivo;

    @OneToMany(mappedBy = "desafioDistintivo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesafioDistintivoFeito> desafiosFeitos;

    protected DesafioDistintivo() {}
}
