package lt.viko.eif.gbudreckyte.airline.repository;

import lt.viko.eif.gbudreckyte.airline.model.BookedTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookedTicketRepository extends JpaRepository<BookedTicket, Long> {

    int countByTicket_Flight_Id(Long flightId);

    List<BookedTicket> findByTicket_Flight_Id(Long flightId);
}
