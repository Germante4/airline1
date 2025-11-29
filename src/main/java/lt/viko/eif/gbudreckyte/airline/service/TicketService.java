package lt.viko.eif.gbudreckyte.airline.service;

import lt.viko.eif.gbudreckyte.airline.model.Flight;
import lt.viko.eif.gbudreckyte.airline.model.Ticket;
import lt.viko.eif.gbudreckyte.airline.repository.BookedTicketRepository;
import lt.viko.eif.gbudreckyte.airline.repository.FlightRepository;
import lt.viko.eif.gbudreckyte.airline.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final BookedTicketRepository bookedTicketRepository;
    private final FlightRepository flightRepository;

    public TicketService(
            TicketRepository ticketRepository,
            FlightRepository flightRepository,
            BookedTicketRepository bookedTicketRepository
    ) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.bookedTicketRepository = bookedTicketRepository;
    }

    // ---------------------------
    // CRUD
    // ---------------------------

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Long id, Ticket updated) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setSeatNumber(updated.getSeatNumber());
        ticket.setPrice(updated.getPrice());
        ticket.setFlight(updated.getFlight());

        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket getTicket(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    // ---------------------------
    // BUSINESS LOGIC
    // ---------------------------

    public int getRemainingTickets(Long flightId) {
        int total = ticketRepository.countByFlight_Id(flightId);
        int booked = bookedTicketRepository.countByTicket_Flight_Id(flightId);
        return total - booked;
    }
/*
    public boolean isSeatAvailable(Long flightId, int seatNumber) {
        return ticketRepository.findByFlight_IdAndSeatNumber(flightId, seatNumber)
                .isEmpty();
    }

    public List<Integer> getAvailableSeats(Long flightId) {
        List<Ticket> tickets = ticketRepository.findByFlight_Id(flightId);
        List<Long> booked = bookedTicketRepository.findTicketIdsByFlight(flightId);

        List<Integer> available = new ArrayList<>();

        for (Ticket t : tickets) {
            if (!booked.contains(t.getId())) {
                available.add(t.getSeatNumber());
            }
        }
        return available;
    }
*/
    // ---------------------------
    // AUTO-GENERATE TICKETS (by aircraft capacity)
    // ---------------------------

    public void generateTicketsForFlight(Flight flight) {
        int capacity = flight.getAircraft().getCapacity();

        for (int seat = 1; seat <= capacity; seat++) {
            Ticket ticket = new Ticket();
            ticket.setFlight(flight);
            ticket.setSeatNumber(seat + "");
            ticket.setPrice(99.99);

            ticketRepository.save(ticket);
        }
    }
}
