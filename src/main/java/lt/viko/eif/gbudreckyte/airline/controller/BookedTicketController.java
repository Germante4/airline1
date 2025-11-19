package lt.viko.eif.gbudreckyte.airline.controller;

import lombok.RequiredArgsConstructor;
import lt.viko.eif.gbudreckyte.airline.model.BookedTicket;
import lt.viko.eif.gbudreckyte.airline.repository.BookedTicketRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booked-tickets")
@RequiredArgsConstructor
public class BookedTicketController {

    private final BookedTicketRepository repo;

    @GetMapping
    public List<BookedTicket> getAll() {
        return repo.findAll();
    }

    @PostMapping
    public BookedTicket create(@RequestBody BookedTicket bookedTicket) {
        return repo.save(bookedTicket);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
