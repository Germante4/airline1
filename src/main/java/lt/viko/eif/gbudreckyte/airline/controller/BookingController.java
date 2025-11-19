package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Booking;
import lt.viko.eif.gbudreckyte.airline.repository.BookingRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    @PostMapping
    public Booking create(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }
}
