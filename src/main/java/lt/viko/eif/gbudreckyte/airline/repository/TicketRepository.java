package lt.viko.eif.gbudreckyte.airline.repository;

import lt.viko.eif.gbudreckyte.airline.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    int countByFlight_Id(Long flightId);

    List<Ticket> findByFlight_Id(Long flightId);

    List<Ticket> findByFlight_IdAndSeatNumber(Long flightId, String seatNumber);
}
