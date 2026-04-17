package raffaele.U5_w2_d5.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="dipendenti")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Dipendente {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String img;


    public Dipendente(String username, String email, String cognome, String nome) {
        this.username = username;
        this.img = "https://picsum.photos/200/300";
        this.email = email;
        this.cognome = cognome;
        this.nome = nome;
    }
}

