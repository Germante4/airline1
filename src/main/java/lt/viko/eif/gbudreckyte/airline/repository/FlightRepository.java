package lt.viko.eif.gbudreckyte.airline.repository;

import lt.viko.eif.gbudreckyte.airline.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}