package raffaele.U5_w2_d5.entities;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@NoArgsConstructor
// ho dovuto togliere @AllArgsConstructor perche mi rompeva tutto
// lombok non mi piace piu hahahahaha..meglio fare a mano..dall prossima faccio a mano che non tribbolo
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
    private LocalDate data;
    @Column
    private String descrizione;

    public Prenotazione(Dipendente dipendente, Viaggio viaggio, LocalDate data, String descrizione) {
        this.dipendente = dipendente;
        this.viaggio = viaggio;
        this.data = data;
        this.descrizione = descrizione;
    }
}
