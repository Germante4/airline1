package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.dto.BookingDTO;
import lt.viko.eif.gbudreckyte.airline.model.*;
import lt.viko.eif.gbudreckyte.airline.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;
    private final BookedTicketRepository bookedTicketRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public BookingController(
            BookingRepository bookingRepository,
            BookedTicketRepository bookedTicketRepository,
            TicketRepository ticketRepository,
            UserRepository userRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.bookedTicketRepository = bookedTicketRepository;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking create(@RequestBody BookingDTO dto) {

        User user = userRepository.findById(dto.userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setStatus(dto.status);

        booking = bookingRepository.save(booking);

        for (Long ticketId : dto.ticketIds) {
            Ticket ticket = ticketRepository.findById(ticketId)
                    .orElseThrow(() -> new RuntimeException("Ticket not found"));

            bookedTicketRepository.save(new BookedTicket(booking, ticket));
        }

        return booking;
    }
}
