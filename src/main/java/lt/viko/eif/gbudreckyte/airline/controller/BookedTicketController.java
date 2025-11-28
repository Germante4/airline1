package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.BookedTicket;
import lt.viko.eif.gbudreckyte.airline.model.Booking;
import lt.viko.eif.gbudreckyte.airline.model.Ticket;
import lt.viko.eif.gbudreckyte.airline.repository.BookedTicketRepository;
import lt.viko.eif.gbudreckyte.airline.repository.BookingRepository;
import lt.viko.eif.gbudreckyte.airline.repository.TicketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booked-tickets")
public class BookedTicketController {

    private final BookedTicketRepository bookedTicketRepository;
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;

    public BookedTicketController(
            BookedTicketRepository bookedTicketRepository,
            BookingRepository bookingRepository,
            TicketRepository ticketRepository
    ) {
        this.bookedTicketRepository = bookedTicketRepository;
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
    }

    // ----------------------------------------------------
    // GET ALL — for admin/testing
    // ----------------------------------------------------
    @GetMapping
    public List<BookedTicket> getAll() {
        return bookedTicketRepository.findAll();
    }

    // ----------------------------------------------------
    // GET BY ID
    // ----------------------------------------------------
    @GetMapping("/{id}")
    public BookedTicket getOne(@PathVariable Long id) {
        return bookedTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BookedTicket not found"));
    }

    // ----------------------------------------------------
    // CREATE — add a ticket to a booking
    // ----------------------------------------------------
    @PostMapping
    public BookedTicket create(@RequestParam Long bookingId, @RequestParam Long ticketId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        BookedTicket bookedTicket = new BookedTicket(booking, ticket);
        return bookedTicketRepository.save(bookedTicket);
    }

    // ----------------------------------------------------
    // DELETE — remove ticket from booking
    // ----------------------------------------------------
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        bookedTicketRepository.deleteById(id);
        return "Booked ticket deleted";
    }
}
