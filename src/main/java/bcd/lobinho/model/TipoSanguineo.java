package bcd.lobinho.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
public class TipoSanguineo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoSanguineo;

    private String tipo;

    @OneToMany(mappedBy = "tipoSanguineo")
    private List<Pessoa> pessoas;

    protected TipoSanguineo() {}
}