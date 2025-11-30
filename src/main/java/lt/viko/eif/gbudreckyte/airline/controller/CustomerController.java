package lt.viko.eif.gbudreckyte.airline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    @GetMapping("/customer/dashboard")
    public String dashboard() {
        return "customer-dashboard.html";
    }

    @GetMapping("/customer/flights")
    public String flights() {
        return "customer-flights.html";
    }

    @GetMapping("/customer/bookings")
    public String bookings() {
        return "customer-bookings.html";
    }

    @GetMapping("/customer/profile")
    public String profile() {
        return "customer-profile.html";
    }

    @GetMapping("customer/book")
    public String book() {
        return "customer-book.html";
    }
}
