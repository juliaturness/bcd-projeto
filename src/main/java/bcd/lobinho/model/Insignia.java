package bcd.lobinho.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Data
@Entity
@Table(name = "insignia")
public class Insignia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_insignia")
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nome;

    @OneToMany(mappedBy = "insignia", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DesafioInsignia> desafios;
}