package raffaele.U5_w2_d5.entities;


import jakarta.persistence.*;
import lombok.*;
import raffaele.U5_w2_d5.enumm.StatoViaggio;

import java.time.LocalDate;

@Entity
@Table(name="viaggi")
@Getter
@Setter
@AllArgsConstructor
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoViaggio stato;
}
