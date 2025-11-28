package lt.viko.eif.gbudreckyte.airline.dto;

import java.util.List;

public class BookingDTO {
    public Long userId;
    public List<Long> ticketIds;  // list of tickets the user wants to book
    public String status;
}
