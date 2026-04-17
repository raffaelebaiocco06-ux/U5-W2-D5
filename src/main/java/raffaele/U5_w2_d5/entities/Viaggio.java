package raffaele.U5_w2_d5.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="viaggi")
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Viaggio {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String destinazione;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private String stato;

    public Viaggio(String destinazione, LocalDate data, String stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }
}
