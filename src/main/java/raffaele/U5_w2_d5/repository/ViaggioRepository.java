package raffaele.U5_w2_d5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raffaele.U5_w2_d5.entities.Viaggio;

import java.time.LocalDate;

public interface ViaggioRepository extends JpaRepository<Viaggio, Long> {
    boolean existsByData(LocalDate data);
}
