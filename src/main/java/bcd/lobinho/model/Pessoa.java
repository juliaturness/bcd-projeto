package bcd.lobinho.model;


import jakarta.persistence.*;


@Entity
@Inheritance
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

}
