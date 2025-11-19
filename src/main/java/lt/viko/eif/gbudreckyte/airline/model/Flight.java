package lt.viko.eif.gbudreckyte.airline.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @Column(name = "aircraft_id")
    private Long aircraftId;

    @Column(name = "origin_airport_id")
    private Long originAirportId;

    @Column(name = "destination_airport_id")
    private Long destinationAirportId;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    private String status;

    // getters and setters
}
