package lt.viko.eif.gbudreckyte.airline.service;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.gbudreckyte.airline.model.Flight;
import lt.viko.eif.gbudreckyte.airline.model.Ticket;
import lt.viko.eif.gbudreckyte.airline.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    public void generateTicketsForFlight(Flight flight) {

        for (int i = 1; i <= 20; i++) {
            Ticket t = new Ticket();
            t.setFlight(flight);
            t.setSeatNumber("Seat " + i);
            t.setPrice(99.99); // you can customize later

            ticketRepository.save(t);
        }
    }
}
