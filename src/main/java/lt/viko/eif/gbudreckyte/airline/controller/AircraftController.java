package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.model.Aircraft;
import lt.viko.eif.gbudreckyte.airline.repository.AircraftRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aircraft")
public class AircraftController {

    private final AircraftRepository aircraftRepository;

    public AircraftController(AircraftRepository aircraftRepository) {
        this.aircraftRepository = aircraftRepository;
    }

    // CUSTOMER — view only
    @GetMapping
    public List<Aircraft> getAll() {
        return aircraftRepository.findAll();
    }

    @GetMapping("/{id}")
    public Aircraft getOne(@PathVariable Long id) {
        return aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));
    }

    // ADMIN — full management
    @PostMapping("/admin")
    public Aircraft create(@RequestBody Aircraft aircraft) {
        return aircraftRepository.save(aircraft);
    }

    @PutMapping("/admin/{id}")
    public Aircraft update(@PathVariable Long id, @RequestBody Aircraft updated) {
        Aircraft aircraft = aircraftRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        aircraft.setModel(updated.getModel());
        aircraft.setCapacity(updated.getCapacity());

        return aircraftRepository.save(aircraft);
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable Long id) {
        aircraftRepository.deleteById(id);
        return "Aircraft deleted";
    }
}
