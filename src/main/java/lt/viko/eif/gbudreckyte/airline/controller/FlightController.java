package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Flight;
import lt.viko.eif.gbudreckyte.airline.repository.FlightRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @PostMapping
    public Flight create(@RequestBody Flight flight) {
        return flightRepository.save(flight);
    }
}
