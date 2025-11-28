package lt.viko.eif.gbudreckyte.airline.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who made the booking
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    private String status;

    // A booking can have many booked tickets
    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL)
    private List<BookedTicket> bookedTickets = new ArrayList<>();

    public Booking() {}

    // getters & setters
    public Long getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<BookedTicket> getBookedTickets() { return bookedTickets; }
    public void setBookedTickets(List<BookedTicket> bookedTickets) { this.bookedTickets = bookedTickets; }
}
