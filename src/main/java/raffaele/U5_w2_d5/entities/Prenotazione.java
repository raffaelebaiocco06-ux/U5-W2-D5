package raffaele.U5_w2_d5.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "dipendente-id", nullable = false)
    private Dipendente dipendente;
    @ManyToOne(optional = false)
    @JoinColumn(name = "viaggio-id", nullable = false)
    private Viaggio viaggio;
    @Column(nullable = false)
    private LocalDate dataRichiesta;
    @Column
    private String descrizione;
}
