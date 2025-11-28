package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Airport;
import lt.viko.eif.gbudreckyte.airline.repository.AirportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    // CUSTOMER — view only
    @GetMapping
    public List<Airport> getAll() {
        return airportRepository.findAll();
    }

    @GetMapping("/{id}")
    public Airport getOne(@PathVariable Long id) {
        return airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    // ADMIN — create/update/delete
    @PostMapping("/admin")
    public Airport create(@RequestBody Airport airport) {
        return airportRepository.save(airport);
    }

    @PutMapping("/admin/{id}")
    public Airport update(@PathVariable Long id, @RequestBody Airport updated) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airport not found"));

        airport.setCode(updated.getCode());
        airport.setName(updated.getName());
        airport.setCity(updated.getCity());
        airport.setCountry(updated.getCountry());
        airport.setLatitude(updated.getLatitude());
        airport.setLongitude(updated.getLongitude());

        return airportRepository.save(airport);
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable Long id) {
        airportRepository.deleteById(id);
        return "Airport deleted";
    }
}
