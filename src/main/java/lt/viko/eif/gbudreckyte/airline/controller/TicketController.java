package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.dto.TicketDTO;
import lt.viko.eif.gbudreckyte.airline.model.Flight;
import lt.viko.eif.gbudreckyte.airline.model.Ticket;
import lt.viko.eif.gbudreckyte.airline.repository.BookedTicketRepository;
import lt.viko.eif.gbudreckyte.airline.repository.FlightRepository;
import lt.viko.eif.gbudreckyte.airline.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final BookedTicketRepository bookedTicketRepository;
    private final FlightRepository flightRepository;

    public TicketController(TicketRepository ticketRepository, FlightRepository flightRepository, BookedTicketRepository bookedTicketRepository) {
        this.ticketRepository = ticketRepository;
        this.flightRepository = flightRepository;
        this.bookedTicketRepository = bookedTicketRepository;
    }

    // CUSTOMER — view tickets
    @GetMapping
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @GetMapping("/{id}")
    public Ticket getOne(@PathVariable Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    // ADMIN — create ticket
    @PostMapping("/admin")
    public Ticket create(@RequestBody TicketDTO dto) {

        Flight flight = flightRepository.findById(dto.flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        ticket.setSeatNumber(dto.seatNumber);
        ticket.setPrice(dto.price);

        return ticketRepository.save(ticket);
    }

    // ADMIN — update ticket
    @PutMapping("/admin/{id}")
    public Ticket update(@PathVariable Long id, @RequestBody TicketDTO dto) {

        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Flight flight = flightRepository.findById(dto.flightId)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        ticket.setFlight(flight);
        ticket.setSeatNumber(dto.seatNumber);
        ticket.setPrice(dto.price);

        return ticketRepository.save(ticket);
    }

    // ADMIN — delete ticket
    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable Long id) {
        ticketRepository.deleteById(id);
        return "Ticket deleted";
    }
    @GetMapping("/remaining/{flightId}")
    public int getRemaining(@PathVariable Long flightId) {

        int total = ticketRepository.countByFlight_Id(flightId);
        int booked = bookedTicketRepository.countByTicket_Flight_Id(flightId);

        return total - booked;
    }
}
