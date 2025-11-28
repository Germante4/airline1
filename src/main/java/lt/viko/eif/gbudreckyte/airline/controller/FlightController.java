package lt.viko.eif.gbudreckyte.airline.controller;

import lt.viko.eif.gbudreckyte.airline.dto.FlightDTO;
import lt.viko.eif.gbudreckyte.airline.model.Aircraft;
import lt.viko.eif.gbudreckyte.airline.model.Airport;
import lt.viko.eif.gbudreckyte.airline.model.Flight;
import lt.viko.eif.gbudreckyte.airline.repository.AircraftRepository;
import lt.viko.eif.gbudreckyte.airline.repository.AirportRepository;
import lt.viko.eif.gbudreckyte.airline.repository.FlightRepository;
import lt.viko.eif.gbudreckyte.airline.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightRepository flightRepository;
    private final AircraftRepository aircraftRepository;
    private final AirportRepository airportRepository;
    private final TicketService ticketService;


    public FlightController(
            FlightRepository flightRepository,
            AircraftRepository aircraftRepository,
            AirportRepository airportRepository,
            TicketService ticketService) {
        this.flightRepository = flightRepository;
        this.aircraftRepository = aircraftRepository;
        this.airportRepository = airportRepository;
        this.ticketService = ticketService;
    }


    // --------------------
    // CUSTOMER — VIEW ONLY
    // --------------------
    @GetMapping
    public List<Flight> getAll() {
        return flightRepository.findAll();
    }

    @GetMapping("/{id}")
    public Flight getOne(@PathVariable Long id) {
        return flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    // --------------------
    // ADMIN — CREATE
    // --------------------
    @PostMapping("/admin")
    public Flight create(@RequestBody FlightDTO dto) {

        Aircraft aircraft = aircraftRepository.findById(dto.aircraftId)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        Airport origin = airportRepository.findById(dto.originAirportId)
                .orElseThrow(() -> new RuntimeException("Origin airport not found"));

        Airport destination = airportRepository.findById(dto.destinationAirportId)
                .orElseThrow(() -> new RuntimeException("Destination airport not found"));

        Flight flight = new Flight();
        flight.setFlightNumber(dto.flightNumber);
        flight.setAircraft(aircraft);
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setDepartureTime(dto.departureTime);
        flight.setArrivalTime(dto.arrivalTime);
        flight.setStatus(dto.status);

        // 1️⃣ First save flight
        Flight saved = flightRepository.save(flight);

        // 2️⃣ Then generate tickets for it
        ticketService.generateTicketsForFlight(saved);

        return saved;
    }

    // --------------------
    // ADMIN — UPDATE
    // --------------------
    @PutMapping("/admin/{id}")
    public Flight update(@PathVariable Long id, @RequestBody FlightDTO dto) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Aircraft aircraft = aircraftRepository.findById(dto.aircraftId)
                .orElseThrow(() -> new RuntimeException("Aircraft not found"));

        Airport origin = airportRepository.findById(dto.originAirportId)
                .orElseThrow(() -> new RuntimeException("Origin airport not found"));

        Airport destination = airportRepository.findById(dto.destinationAirportId)
                .orElseThrow(() -> new RuntimeException("Destination airport not found"));

        flight.setFlightNumber(dto.flightNumber);
        flight.setAircraft(aircraft);
        flight.setOriginAirport(origin);
        flight.setDestinationAirport(destination);
        flight.setDepartureTime(dto.departureTime);
        flight.setArrivalTime(dto.arrivalTime);
        flight.setStatus(dto.status);

        return flightRepository.save(flight);
    }

    // --------------------
    // ADMIN — DELETE
    // --------------------
    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable Long id) {
        flightRepository.deleteById(id);
        return "Flight deleted";
    }

    // --------------------
    // STAFF — STATUS ONLY
    // --------------------
    @PatchMapping("/status/{id}")
    public Flight updateStatus(@PathVariable Long id, @RequestParam String status) {

        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        flight.setStatus(status);
        return flightRepository.save(flight);
    }
    @GetMapping("/search")
    public List<Flight> searchFlights(
            @RequestParam(required = false) Long originAirportId,
            @RequestParam(required = false) Long destinationAirportId,
            @RequestParam(required = false) String date
    ) {
        List<Flight> flights = flightRepository.findAll();

        if (originAirportId != null) {
            flights = flights.stream()
                    .filter(f -> f.getOriginAirport().getId().equals(originAirportId))
                    .toList();
        }

        if (destinationAirportId != null) {
            flights = flights.stream()
                    .filter(f -> f.getDestinationAirport().getId().equals(destinationAirportId))
                    .toList();
        }

        if (date != null) {
            flights = flights.stream()
                    .filter(f -> f.getDepartureTime().toLocalDate().toString().equals(date))
                    .toList();
        }

        return flights;
    }

}
