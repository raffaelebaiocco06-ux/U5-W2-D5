package raffaele.U5_w2_d5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raffaele.U5_w2_d5.entities.Prenotazione;

import java.time.LocalDate;

public interface PrenotazoneRepository extends JpaRepository<Prenotazione,Long> {
    boolean existsByDipendenteIdAndViaggioData(Long dipendenteId, LocalDate data);
}
