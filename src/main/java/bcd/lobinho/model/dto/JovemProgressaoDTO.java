package bcd.lobinho.model.dto;

import bcd.lobinho.model.*;
import lombok.Data;
import java.util.List;

@Data
public class JovemProgressaoDTO {
    private Pessoa jovem;
    private List<Especialidade> especialidades;
    private List<Insignia> insignias;
}