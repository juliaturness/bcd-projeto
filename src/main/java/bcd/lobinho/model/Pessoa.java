package bcd.lobinho.model;


import jakarta.persistence.*;


@Entity
@Table(name = "Pessoa")
public class Pessoa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String cpf;
    @Column(nullable = false)
    private String endereco;
    private String telefone;
    private String dataNascimento;
    private String genero;


    @ManyToOne
    @JoinColumn(name = "id_tipo_sanguineo")
    private TipoSanguineo tipoSanguineo;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private DadosSaude dadoSaude;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private Vinculo vinculo;

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private // desafios destinitvos

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private // noites acampapdas

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private // desafois insignia

    @OneToOne(mappedBy = "pessoa", cascade = CascadeType.ALL)
    private // desafio especialidade 


}
