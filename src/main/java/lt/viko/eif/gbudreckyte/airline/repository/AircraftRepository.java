package lt.viko.eif.gbudreckyte.airline.repository;

import lt.viko.eif.gbudreckyte.airline.model.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
}
