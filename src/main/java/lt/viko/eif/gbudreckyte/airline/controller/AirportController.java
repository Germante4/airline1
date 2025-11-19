package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Airport;
import lt.viko.eif.gbudreckyte.airline.repository.AirportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @GetMapping
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @PostMapping
    public Airport create(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }
}
