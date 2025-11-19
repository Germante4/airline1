package lt.viko.eif.gbudreckyte.airline.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booked_ticket")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookedTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "ticket_id", nullable = false)
    private Ticket ticket;
}
