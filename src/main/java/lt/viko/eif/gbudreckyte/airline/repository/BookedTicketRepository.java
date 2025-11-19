package lt.viko.eif.gbudreckyte.airline.repository;

import lt.viko.eif.gbudreckyte.airline.model.BookedTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Long> {
}
