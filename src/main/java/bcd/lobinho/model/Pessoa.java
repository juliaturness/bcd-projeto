package bcd.lobinho.model;


import jakarta.persistence.*;


@Entity
@Inheritance
public class Pessoa {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String cpf;
    private String endereco;
    private String telefone;
    private String dataNascimento;
    private String genero;

}
