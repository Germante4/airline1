package lt.viko.eif.gbudreckyte.airline.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class FlightDTO {

    public String flightNumber;

    public Long aircraftId;
    public Long originAirportId;
    public Long destinationAirportId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime departureTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    public LocalDateTime arrivalTime;

    public String status;
}
