package raffaele.U5_w2_d5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import raffaele.U5_w2_d5.entities.Dipendente;

import java.util.Optional;

public interface DipendenteRepository extends JpaRepository<Dipendente, Long> {
    boolean existsByEmail(String email);

}
