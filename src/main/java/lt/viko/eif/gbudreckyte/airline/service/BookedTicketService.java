package lt.viko.eif.gbudreckyte.airline.service;

import lt.viko.eif.gbudreckyte.airline.model.BookedTicket;
import lt.viko.eif.gbudreckyte.airline.model.Booking;
import lt.viko.eif.gbudreckyte.airline.model.Ticket;
import lt.viko.eif.gbudreckyte.airline.repository.BookedTicketRepository;
import lt.viko.eif.gbudreckyte.airline.repository.BookingRepository;
import lt.viko.eif.gbudreckyte.airline.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedTicketService {

    private final BookedTicketRepository bookedTicketRepository;
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;

    public BookedTicketService(
            BookedTicketRepository bookedTicketRepository,
            BookingRepository bookingRepository,
            TicketRepository ticketRepository
    ) {
        this.bookedTicketRepository = bookedTicketRepository;
        this.bookingRepository = bookingRepository;
        this.ticketRepository = ticketRepository;
    }

    public List<BookedTicket> getAll() {
        return bookedTicketRepository.findAll();
    }

    public BookedTicket getOne(Long id) {
        return bookedTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booked ticket not found"));
    }

    public BookedTicket create(Long bookingId, Long ticketId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        BookedTicket bt = new BookedTicket(booking, ticket);
        return bookedTicketRepository.save(bt);
    }

    public void delete(Long id) {
        bookedTicketRepository.deleteById(id);
    }
}
