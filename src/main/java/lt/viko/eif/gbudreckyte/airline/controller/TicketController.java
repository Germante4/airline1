package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Ticket;
import lt.viko.eif.gbudreckyte.airline.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAll() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getOne(@PathVariable Long id) {
        return ticketService.getTicket(id);
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "Ticket deleted";
    }

    @GetMapping("/remaining/{flightId}")
    public int getRemaining(@PathVariable Long flightId) {
        return ticketService.getRemainingTickets(flightId);
    }
}
