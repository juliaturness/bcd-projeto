package bcd.lobinho.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tipo_sanguineo")
public class TipoSanguineo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tipo;

    @OneToMany(mappedBy = "tipoSanguineo")
    private List<Pessoa> pessoas;
}