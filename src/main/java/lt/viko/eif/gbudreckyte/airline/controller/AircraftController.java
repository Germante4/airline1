package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Aircraft;
import lt.viko.eif.gbudreckyte.airline.repository.AircraftRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {

    private final AircraftRepository repository;

    public AircraftController(AircraftRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Aircraft> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Aircraft create(@RequestBody Aircraft aircraft) {
        return repository.save(aircraft);
    }
}
