package lt.viko.eif.gbudreckyte.airline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin-dashboard.html"; // resolves templates/admin-dashboard.html
    }

    @GetMapping("/flights")
    public String flightsPage() {
        return "admin-flights.html";
    }

    @GetMapping("/aircraft")
    public String aircraftPage() {
        return "admin-aircraft.html";
    }

    @GetMapping("/airports")
    public String airportsPage() {
        return "admin-airports.html";
    }

    @GetMapping("/bookings")
    public String bookingsPage() {
        return "admin-bookings.html";
    }

    @GetMapping("/users")
    public String usersPage() {
        return "admin-users.html";
    }
}
