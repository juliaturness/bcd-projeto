package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id_pessoa;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String endereco;

    private String telefone;
    private LocalDate dataNascimento;
    private String genero;


    @ManyToOne
    @JoinColumn(name = "id_tipo_sanguineo")
    private TipoSanguineo tipoSanguineo;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private DadosSaude dadoSaude;

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private List<Vinculo> vinculos;

    @OneToMany(mappedBy = "pessoa")
    private List<DesafioDistintivoFeito> desafiosDistintivos;

    @OneToMany(mappedBy = "pessoa")
    private List<NoiteAcampada> noitesAcampadas;

    @OneToMany(mappedBy = "pessoa")
    private List<DesafioInsigniaFeito> desafiosInsignias;

    @OneToMany(mappedBy = "pessoa")
    private List<DesafioEspecialidadeFeito> desafiosEspecialidades;


}
